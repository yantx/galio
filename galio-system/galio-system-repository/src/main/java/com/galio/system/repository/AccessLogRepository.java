package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.core.utils.StringUtil;
import com.galio.system.mapper.AccessLogMapper;
import com.galio.system.entity.AccessLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class AccessLogRepository {

    private final AccessLogMapper accessLogMapper;

    /**
     * 查询系统访问记录
     */
    public AccessLog selectById(Long accessId) {
        return accessLogMapper.selectById(accessId);
    }

        /**
         * 查询系统访问记录列表
         */
        public Page<AccessLog> selectPage(Page page) {
            LambdaQueryWrapper<AccessLog> lqw = Wrappers.lambdaQuery();
            return accessLogMapper.selectPage(page, lqw);
        }

    /**
     * 查询系统访问记录列表
     */
    public List<AccessLog> selectList(AccessLog accessLog) {
        LambdaQueryWrapper<AccessLog> lqw = buildQueryWrapper(accessLog);
        return accessLogMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<AccessLog> buildQueryWrapper(AccessLog entity) {
        LambdaQueryWrapper<AccessLog> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getUsername()), AccessLog::getUsername, entity.getUsername());
                    lqw.eq(entity.getMemberId() != null, AccessLog::getMemberId, entity.getMemberId());
                    lqw.eq(StringUtil.isNotBlank(entity.getIpaddr()), AccessLog::getIpaddr, entity.getIpaddr());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), AccessLog::getStatus, entity.getStatus());
                    lqw.eq(StringUtil.isNotBlank(entity.getMsg()), AccessLog::getMsg, entity.getMsg());
                    lqw.eq(entity.getAccessTime() != null, AccessLog::getAccessTime, entity.getAccessTime());
                    lqw.eq(entity.getAppId() != null, AccessLog::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增系统访问记录
     */
    public int insert(AccessLog entity) {
        validEntityBeforeSave(entity);
        int flag = accessLogMapper.insert(entity);
        return flag;
    }

    /**
     * 修改系统访问记录
     */
    public int updateById(AccessLog entity) {
        validEntityBeforeSave(entity);
        return accessLogMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AccessLog entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除系统访问记录
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return accessLogMapper.deleteBatchIds(ids);
    }
}
