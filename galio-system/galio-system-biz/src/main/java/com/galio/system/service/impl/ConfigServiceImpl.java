package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
    import com.galio.mybatis.page.PageDto;
    import com.galio.mybatis.page.PageVo;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.ConfigDto;
import com.galio.system.model.vo.ConfigVo;
import com.galio.system.model.Config;
import com.galio.system.mapper.ConfigMapper;
import com.galio.system.service.ConfigService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 应用信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class ConfigServiceImpl implements ConfigService {

    private final ConfigMapper configMapper;

    /**
     * 查询应用信息
     */
    @Override
    public ConfigVo queryById(Long configId) {
        return configMapper.selectVoById(configId);
    }

        /**
         * 查询应用信息列表
         */
        @Override
        public PageVo<ConfigVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Config> lqw = Wrappers.lambdaQuery();
            IPage<ConfigVo> pageData = configMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<ConfigVo> queryList(ConfigDto dto) {
        LambdaQueryWrapper<Config> lqw = buildQueryWrapper(dto);
        return configMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Config> buildQueryWrapper(ConfigDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Config> lqw = Wrappers.lambdaQuery();
                    lqw.like(StringUtil.isNotBlank(dto.getConfigName()), Config::getConfigName, dto.getConfigName());
                    lqw.eq(StringUtil.isNotBlank(dto.getConfigKey()), Config::getConfigKey, dto.getConfigKey());
                    lqw.eq(StringUtil.isNotBlank(dto.getConfigValue()), Config::getConfigValue, dto.getConfigValue());
                    lqw.eq(StringUtil.isNotBlank(dto.getConfigType()), Config::getConfigType, dto.getConfigType());
                    lqw.eq(dto.getAppId() != null, Config::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByDto(ConfigDto dto) {
        Config add = ObjectUtil.copyObject(dto, Config. class);
        validEntityBeforeSave(add);
        boolean flag = configMapper.insert(add) > 0;
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
        Config update = ObjectUtil.copyObject(dto, Config. class);
        validEntityBeforeSave(update);
        return configMapper.updateById(update) > 0;
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
        return configMapper.deleteBatchIds(ids) > 0;
    }
}
