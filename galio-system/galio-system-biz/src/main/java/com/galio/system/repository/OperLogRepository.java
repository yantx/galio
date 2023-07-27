package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.OperLog;
import com.galio.system.mapper.OperLogMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 操作日志记录Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class OperLogRepository{

    private final OperLogMapper operLogMapper;

    /**
     * 查询操作日志记录
     */
    public OperLog selectById(Long operId) {
        return operLogMapper.selectById(operId);
    }

        /**
         * 查询操作日志记录列表
         */
        public Page<OperLog> selectPage(Page page) {
            LambdaQueryWrapper<OperLog> lqw = Wrappers.lambdaQuery();
            return operLogMapper.selectPage(page, lqw);
        }

    /**
     * 查询操作日志记录列表
     */
    public List<OperLog> selectList(OperLog operLog) {
        LambdaQueryWrapper<OperLog> lqw = buildQueryWrapper(operLog);
        return operLogMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<OperLog> buildQueryWrapper(OperLog entity) {
        LambdaQueryWrapper<OperLog> lqw = Wrappers.lambdaQuery();
                    lqw.eq(StringUtil.isNotBlank(entity.getTitle()), OperLog::getTitle, entity.getTitle());
                    lqw.eq(StringUtil.isNotBlank(entity.getOperType()), OperLog::getOperType, entity.getOperType());
                    lqw.eq(StringUtil.isNotBlank(entity.getMethod()), OperLog::getMethod, entity.getMethod());
                    lqw.eq(StringUtil.isNotBlank(entity.getRequestMethod()), OperLog::getRequestMethod, entity.getRequestMethod());
                    lqw.eq(StringUtil.isNotBlank(entity.getOperSide()), OperLog::getOperSide, entity.getOperSide());
                    lqw.eq(entity.getOperBy() != null, OperLog::getOperBy, entity.getOperBy());
                    lqw.like(StringUtil.isNotBlank(entity.getOrgName()), OperLog::getOrgName, entity.getOrgName());
                    lqw.eq(StringUtil.isNotBlank(entity.getOperUrl()), OperLog::getOperUrl, entity.getOperUrl());
                    lqw.eq(StringUtil.isNotBlank(entity.getOperIp()), OperLog::getOperIp, entity.getOperIp());
                    lqw.eq(StringUtil.isNotBlank(entity.getOperLocation()), OperLog::getOperLocation, entity.getOperLocation());
                    lqw.eq(StringUtil.isNotBlank(entity.getOperParam()), OperLog::getOperParam, entity.getOperParam());
                    lqw.eq(StringUtil.isNotBlank(entity.getJsonResult()), OperLog::getJsonResult, entity.getJsonResult());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), OperLog::getStatus, entity.getStatus());
                    lqw.eq(StringUtil.isNotBlank(entity.getErrorMsg()), OperLog::getErrorMsg, entity.getErrorMsg());
                    lqw.eq(entity.getOperTime() != null, OperLog::getOperTime, entity.getOperTime());
                    lqw.eq(entity.getAppId() != null, OperLog::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增操作日志记录
     */
    public int insert(OperLog entity) {
        validEntityBeforeSave(entity);
        int flag = operLogMapper.insert(entity);
        return flag;
    }

    /**
     * 修改操作日志记录
     */
    public int updateById(OperLog entity) {
        validEntityBeforeSave(entity);
        return operLogMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OperLog entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除操作日志记录
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return operLogMapper.deleteBatchIds(ids);
    }
}
