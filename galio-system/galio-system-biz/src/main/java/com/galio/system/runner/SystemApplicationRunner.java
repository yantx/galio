package com.galio.system.runner;

import com.galio.system.service.ConfigService;
import com.galio.system.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 容器启动时初始化信息
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemApplicationRunner implements ApplicationRunner {

    private final ConfigService configService;
    private final DictService dictService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        configService.loadingConfigCache();
        log.info("加载参数缓存数据成功");
//        dictService.loadingDictCache();
        log.info("加载字典缓存数据成功");
    }

}
