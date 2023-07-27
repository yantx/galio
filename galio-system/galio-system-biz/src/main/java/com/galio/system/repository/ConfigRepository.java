package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Config;
import com.galio.system.mapper.ConfigMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class ConfigRepository{

    private final ConfigMapper configMapper;

    /**
     * 查询应用信息
     */
    public Config selectById(Long configId) {
        return configMapper.selectById(configId);
    }

        /**
         * 查询应用信息列表
         */
        public Page<Config> selectPage(Page page) {
            LambdaQueryWrapper<Config> lqw = Wrappers.lambdaQuery();
            return configMapper.selectPage(page, lqw);
        }

    /**
     * 查询应用信息列表
     */
    public List<Config> selectList(Config config) {
        LambdaQueryWrapper<Config> lqw = buildQueryWrapper(config);
        return configMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Config> buildQueryWrapper(Config entity) {
        LambdaQueryWrapper<Config> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(entity.getConfigName()), Config::getConfigName, entity.getConfigName());
                    lqw.eq(StringUtil.isNotBlank(entity.getConfigKey()), Config::getConfigKey, entity.getConfigKey());
                    lqw.eq(StringUtil.isNotBlank(entity.getConfigValue()), Config::getConfigValue, entity.getConfigValue());
                    lqw.eq(StringUtil.isNotBlank(entity.getConfigType()), Config::getConfigType, entity.getConfigType());
                    lqw.eq(entity.getAppId() != null, Config::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    public int insert(Config entity) {
        validEntityBeforeSave(entity);
        int flag = configMapper.insert(entity);
        return flag;
    }

    /**
     * 修改应用信息
     */
    public int updateById(Config entity) {
        validEntityBeforeSave(entity);
        return configMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Config entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用信息
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return configMapper.deleteBatchIds(ids);
    }
}
