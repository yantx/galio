package com.galio.system.service.impl;

import com.galio.core.utils.StringUtil;
import com.galio.core.utils.ObjectUtil;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.ConfigDto;
import com.galio.system.model.Config;
import com.galio.system.repository.ConfigRepository;
import com.galio.system.service.ConfigService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;

    /**
     * 查询应用信息
     */
    @Override
    public Config queryById(Long configId) {
        return configRepository.selectById(configId);
    }

        /**
         * 查询应用信息列表
         */
        @Override
        public Page<Config> queryPageList(PageDto pageDto) {
            return configRepository.selectPage(pageDto.build());
        }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<Config> queryList(ConfigDto dto) {
        Config entity = ObjectUtil.copyObject(dto, Config.class);
        Map<String, Object> params = dto.getParams();
        return configRepository.selectList(entity,params);
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByDto(ConfigDto dto) {
        Config add = ObjectUtil.copyObject(dto, Config.class);
        validEntityBeforeSave(add);
        boolean flag = configRepository.insert(add) > 0;
        if (flag) {
            dto.setConfigId(add.getConfigId());
        }
        return flag;
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean updateByDto(ConfigDto dto) {
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
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return configRepository.deleteBatchIds(ids) > 0;
    }
}
