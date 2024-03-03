package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.DatasetDTO;
import com.galio.system.entity.Dataset;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据集信息Service接口
 */
public interface DatasetService {

    /**
     * 查询数据集信息
     */
    Dataset getById(Long datasetId);

    /**
     * 查询数据集信息列表
     */
    Page<Dataset> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询数据集信息列表
     */
    List<Dataset> list(DatasetDTO dto);

    /**
     * 修改数据集信息
     */
    Boolean save(DatasetDTO dto);

    /**
     * 修改数据集信息
     */
    Boolean update(DatasetDTO dto);

    /**
     * 校验并批量删除数据集信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
