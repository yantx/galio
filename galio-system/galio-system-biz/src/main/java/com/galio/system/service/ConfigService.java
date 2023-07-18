package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.ConfigDto;
import com.galio.system.model.Config;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统配置信息Service接口
 */
public interface ConfigService {

    /**
     * 查询系统配置信息
     */
    Config queryById(Long configId);

    /**
     * 查询系统配置信息列表
     */
    Page<Config> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询系统配置信息列表
     */
    List<Config> queryList(ConfigDto dto);

    /**
     * 修改系统配置信息
     */
    Boolean insertByDto(ConfigDto dto);

    /**
     * 修改系统配置信息
     */
    Boolean updateByDto(ConfigDto dto);

    /**
     * 校验并批量删除系统配置信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 参数缓存数据
     */
    void loadingConfigCache();
}
