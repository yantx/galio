package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.ConfigDTO;
import com.galio.system.entity.Config;
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
    Config getById(Long configId);

    /**
     * 查询系统配置信息列表
     */
    Page<Config> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询系统配置信息列表
     */
    List<Config> list(ConfigDTO dto);

    /**
     * 修改系统配置信息
     */
    Boolean save(ConfigDTO dto);

    /**
     * 修改系统配置信息
     */
    Boolean update(ConfigDTO dto);

    /**
     * 校验并批量删除系统配置信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);

    /**
     * 参数缓存数据
     */
    void loadingConfigCache();
}
