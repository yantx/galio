package com.galio.system.service;

import com.galio.system.model.vo.DatasetVo;
import com.galio.system.model.dto.DatasetDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据集信息Service接口
 */
public interface DatasetService {

    /**
     * 查询数据集信息
     */
    DatasetVo queryById(Long datasetId);

    /**
     * 查询数据集信息列表
     */
    PageVo<DatasetVo> queryPageList(PageDto pageDto);

    /**
     * 查询数据集信息列表
     */
    List<DatasetVo> queryList(DatasetDto dto);

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
