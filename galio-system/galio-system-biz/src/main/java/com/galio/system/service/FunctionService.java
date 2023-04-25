package com.galio.system.service;

import com.galio.system.model.dto.FunctionDto;
import com.galio.system.model.Function;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 功能Service接口
 */
public interface FunctionService {

    /**
     * 查询功能
     */
    Function queryById(Long functionId);

    /**
     * 查询功能列表
     */
    Page<Function> queryPageList(PageDto pageDto);

    /**
     * 查询功能列表
     */
    List<Function> queryList(FunctionDto dto);

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
