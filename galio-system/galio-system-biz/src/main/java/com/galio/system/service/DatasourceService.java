package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.DatasourceDTO;
import com.galio.system.entity.Datasource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据源信息Service接口
 */
public interface DatasourceService {

    /**
     * 查询数据源信息
     */
    Datasource getById(Long datasourceId);

    /**
     * 查询数据源信息列表
     */
    Page<Datasource> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询数据源信息列表
     */
    List<Datasource> list(DatasourceDTO dto);

    /**
     * 修改数据源信息
     */
    Boolean save(DatasourceDTO dto);

    /**
     * 修改数据源信息
     */
    Boolean update(DatasourceDTO dto);

    /**
     * 校验并批量删除数据源信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
