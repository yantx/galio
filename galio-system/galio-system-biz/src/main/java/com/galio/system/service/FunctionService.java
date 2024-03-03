package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.FunctionDTO;
import com.galio.system.entity.Function;
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
    Function getById(Long functionId);

    /**
     * 查询功能列表
     */
    Page<Function> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询功能列表
     */
    List<Function> list(FunctionDTO dto);
    List<Function> listByRoles(Collection<Long> roleIds);
    List<Function> getTree(List<Function> functionList);

    /**
     * 修改功能
     */
    Boolean save(FunctionDTO dto);

    /**
     * 修改功能
     */
    Boolean update(FunctionDTO dto);

    /**
     * 校验并批量删除功能信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
