package com.galio.system.service;

import com.galio.system.model.vo.DatasourceVo;
import com.galio.system.model.dto.DatasourceDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据源信息Service接口
 */
public interface DatasourceService {

    /**
     * 查询数据源信息
     */
    DatasourceVo queryById(Long datasourceId);

    /**
     * 查询数据源信息列表
     */
    PageVo<DatasourceVo> queryPageList(PageDto pageDto);

    /**
     * 查询数据源信息列表
     */
    List<DatasourceVo> queryList(DatasourceDto dto);

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
