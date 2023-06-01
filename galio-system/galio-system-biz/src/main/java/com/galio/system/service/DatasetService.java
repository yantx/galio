package com.galio.system.service;

import com.galio.system.dto.DatasetDto;
import com.galio.system.model.Dataset;
import com.galio.mybatis.page.PageDto;
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
    Dataset queryById(Long datasetId);

    /**
     * 查询数据集信息列表
     */
    Page<Dataset> queryPageList(PageDto pageDto);

    /**
     * 查询数据集信息列表
     */
    List<Dataset> queryList(DatasetDto dto);

    /**
     * 修改数据集信息
     */
    Boolean insertByDto(DatasetDto dto);

    /**
     * 修改数据集信息
     */
    Boolean updateByDto(DatasetDto dto);

    /**
     * 校验并批量删除数据集信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
