package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.DatasourceDto;
import com.galio.system.model.Datasource;
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
    Datasource queryById(Long datasourceId);

    /**
     * 查询数据源信息列表
     */
    Page<Datasource> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询数据源信息列表
     */
    List<Datasource> queryList(DatasourceDto dto);

    /**
     * 修改数据源信息
     */
    Boolean insertByDto(DatasourceDto dto);

    /**
     * 修改数据源信息
     */
    Boolean updateByDto(DatasourceDto dto);

    /**
     * 校验并批量删除数据源信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
