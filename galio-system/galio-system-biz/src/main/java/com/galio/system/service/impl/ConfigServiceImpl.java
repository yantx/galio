package com.galio.system.service.impl;

import com.galio.core.constant.CacheConstants;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.redis.util.CacheUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.ConfigDTO;
import com.galio.system.entity.Config;
import com.galio.system.repository.ConfigRepository;
import com.galio.system.service.ConfigService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统配置信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;

    /**
     * 查询系统配置信息
     */
    @Override
    public Config getById(Long configId) {
        return configRepository.selectById(configId);
    }

    /**
     * 查询系统配置信息列表
     */
    @Override
    public Page<Config> listPage(PageRequestDTO pageRequestDTO) {
        return configRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
    }

    /**
     * 查询系统配置信息列表
     */
    @Override
    public List<Config> list(ConfigDTO dto) {
        Config entity = ObjectUtil.copyObject(dto, Config.class);
        
        return configRepository.selectList(entity);
    }

    /**
     * 新增系统配置信息
     */
    @Override
    public Boolean save(ConfigDTO dto) {
        Config add = ObjectUtil.copyObject(dto, Config.class);
        validEntityBeforeSave(add);
        boolean flag = configRepository.insert(add) > 0;
        if (flag) {
            dto.setConfigId(add.getConfigId());
        }
        return flag;
    }

    /**
     * 修改系统配置信息
     */
    @Override
    public Boolean update(ConfigDTO dto) {
        Config update = ObjectUtil.copyObject(dto, Config.class);
        validEntityBeforeSave(update);
        return configRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Config entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除系统配置信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        
        return configRepository.deleteBatchIds(ids) > 0;
    }

    @Override
    public void loadingConfigCache() {
        List<Config> configsList = list(new ConfigDTO());
        configsList.forEach(config ->
                CacheUtils.put(CacheConstants.SYS_CONFIG_NAMESPACE, config.getConfigKey(), config.getConfigValue()));
    }
}
