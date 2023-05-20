package com.galio.mybatis.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.utils.Assert;
import com.galio.core.utils.id.UUID;
import com.galio.redis.util.RedisUtils;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: galio
 * @Date: 2023-05-20 06:34:41
 * @Description: 防止雪花算法多节点下ID重复配置
 */
@Slf4j
@Component
public class SnowFlakeWorkerIdAllocator {
    /**
     * workerId可以是0-1023
     */
    private static final int MAX_WORKER_ID = 1024;
    private static final String WORKER_PREFIX = "SnowFlakeWorker-";

    /**
     * worker名称，同一个redis，不同workerName，workerId可以重复，用于共用Redis的情况
     */
    @Value("${idGenerator.workerName:defaultName}")
    private String workerName;
    /**
     * workerKey设置值的ttl
     */
    @Value("${idGenerator.redis.ttl:3600}")
    private int ttl = 3600;
    /**
     * redis续期一次
     */
    @Value("${idGenerator.redis.heartBeatIntervalSecond:60}")
    private long heartBeatIntervalSecond = 60;

    /**
     * spring容器关闭destroy调用后多久后，redisKey才真正删除（通过设置ttl实现）
     */
    @Value("${idGenerator.redis.workerKeyDelayRemoveSecond:60}")
    private long workerKeyDelayRemoveSecond;

    private int snowFlaskWorkerId;
    private String redisValue;
    private ExecutorService executorService;
    private boolean shutdown = false;
    private final ReentrantLock lock = new ReentrantLock();

    public DefaultIdentifierGenerator init() {
        DefaultIdentifierGenerator defaultIdentifierGenerator = setNextSnowFlaskWorkerId();

        executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> new Thread(r, "SnowFlakeWorkerId-HeartBeat-Thread"));
        executorService.execute(new HeartBeatTask());
        return defaultIdentifierGenerator;
    }

    /**
     * 设置雪花算法workerId
     */
    private DefaultIdentifierGenerator setNextSnowFlaskWorkerId() {
        lock.lock();
        try {
            snowFlaskWorkerId = getSnowFlaskWorkerId();
            log.info("雪花算法workerId={}设置成功", snowFlaskWorkerId);
            // 高5bits是数据中心Id，低5big是workerId
            int workerId = snowFlaskWorkerId & 0x1F;
            int dateCenterId = snowFlaskWorkerId >> 5;
            log.info("设置IdWorker, dataCenterId={}, workerId={}", dateCenterId, workerId);
            DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator(workerId, dateCenterId);
            IdWorker.setIdentifierGenerator(defaultIdentifierGenerator);
            // 获取一个雪花id，验证工作机器id位等于snowFlaskWorkerId
            Assert.isTrue(((IdWorker.getId() >> 12) & (MAX_WORKER_ID - 1)) == snowFlaskWorkerId, ResponseEnum.SNOW_FLASK_WORKER_ID_ERROR);
            return defaultIdentifierGenerator;
        } finally {
            lock.unlock();
        }

    }

    /**
     * workerId的释放
     * 关闭心跳线程，删除redis key
     */
    @PreDestroy
    public void destroy() {
        lock.lock();
        try {
            shutdown = true;
            redisValue = null;
        } finally {
            lock.unlock();
        }
        RedisUtils.expire(getWorkerKey(snowFlaskWorkerId), workerKeyDelayRemoveSecond);
        log.info("关闭雪花算法workerId心跳线程");
        executorService.shutdownNow();
    }

    /**
     * 获得可用workerId
     * @return 可用workerId
     */
    private int getSnowFlaskWorkerId() {
        SecureRandom random = new SecureRandom();
        int workerId = random.nextInt(MAX_WORKER_ID);
        String uuid = UUID.randomUUID().toString().replace("-", "");

        boolean success = false;
        // 从随机的位置开始遍历尝试workerId是否已被占用
        for (int i = 0; i < MAX_WORKER_ID && !success; i++) {
            log.info("尝试锁定workerId: {}", workerId);
            RedisUtils.setCacheObject(getWorkerKey(workerId), uuid, Duration.ofSeconds(ttl));
            success = Boolean.TRUE.equals(RedisUtils.isExistsObject(getWorkerKey(workerId)));
            if (!success) {
                log.info("workerId: {} 锁定失败", workerId);
                workerId = (workerId + 1) % MAX_WORKER_ID;
            }
        }
        // 无法找到workerId，抛出异常
        if (!success) {
            // 1024个workerId都尝试了
            throw new RuntimeException("遍历了1-1024个workerId全被占用，无法获取到WorkerId. ");
        }
        redisValue = uuid;
        return workerId;
    }

    /**
     * 存到redis的key
     * @param workerId worker
     * @return key
     */
    private String getWorkerKey(int workerId) {
        return WORKER_PREFIX + workerName + "-" + workerId;
    }


    /**
     * 心跳线程类
     */
    class HeartBeatTask implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                log.info("启动雪花算法workerId心跳线程, key={}, workerId={},每{}s设置ttl={}",
                        getWorkerKey(snowFlaskWorkerId), snowFlaskWorkerId, heartBeatIntervalSecond, ttl);
            } finally {
                lock.unlock();
            }

            while (true) {
                lock.lock();
                try {
                    if (shutdown) {
                        break;
                    }

                    // 设置redis ttl，当做是心跳
                    doHeartBeat();
                } catch (Throwable e) {
                    log.error("雪花算法心跳失败，{}s后重试", heartBeatIntervalSecond, e);
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(heartBeatIntervalSecond * 1000);
                } catch (InterruptedException e) {
                    log.info("线程名称:{}已被中断", Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                }
            }
        }

        /**
         * 进行心跳
         */
        private void doHeartBeat() {
            String workerKey = getWorkerKey(snowFlaskWorkerId);
            String value = RedisUtils.getCacheObject(workerKey);
            if (!redisValue.equals(value)) {
                log.error("雪花算法雪花WorkerId失效，workerId={}已失效或发生冲突, 尝试从新获取雪花Id修复问题", snowFlaskWorkerId);
                // 尝试重新获取workerId
                tryResumeWorkerId();
            } else {
                RedisUtils.expire(workerKey, ttl);
                log.info("心跳成功：雪花算法workerId={}, ttl={}, redisKey={},redisValue={}", snowFlaskWorkerId,
                        ttl, workerKey, redisValue);
            }
        }

        /**
         * 尝试从错误中恢复，重新获取workerId
         */
        private void tryResumeWorkerId() {
            try {
                setNextSnowFlaskWorkerId();
                log.error("雪花算法WorkerId失效问题已修复， 新的雪花workerId={}", snowFlaskWorkerId);
            } catch (Exception e) {
                log.error("致命Error，雪花算法失败，workerId={} 问题修复失败，请尽快重启部署节点！！！", snowFlaskWorkerId, e);
            }
        }
    }
}
