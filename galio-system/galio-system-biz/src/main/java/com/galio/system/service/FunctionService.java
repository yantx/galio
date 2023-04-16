package com.galio.system.service;

import com.galio.system.model.vo.FunctionVo;
import com.galio.system.model.dto.FunctionDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 功能Service接口
 */
public interface FunctionService {

    /**
     * 查询功能
     */
    FunctionVo queryById(Long functionId);

    /**
     * 查询功能列表
     */
    PageVo<FunctionVo> queryPageList(PageDto pageDto);

    /**
     * 查询功能列表
     */
    List<FunctionVo> queryList(FunctionDto dto);

    /**
     * 修改功能
     */
    Boolean insertByDto(FunctionDto dto);

    /**
     * 修改功能
     */
    Boolean updateByDto(FunctionDto dto);

    /**
     * 校验并批量删除功能信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
