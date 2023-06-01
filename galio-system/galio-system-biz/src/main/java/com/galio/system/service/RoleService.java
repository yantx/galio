package com.galio.system.service;

import com.galio.system.dto.RoleDto;
import com.galio.system.model.Role;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息Service接口
 */
public interface RoleService {

    /**
     * 查询角色信息
     */
    Role queryById(Long roleId);

    /**
     * 查询角色信息列表
     */
    Page<Role> queryPageList(PageDto pageDto);

    /**
     * 查询角色信息列表
     */
    List<Role> queryList(RoleDto dto);

    /**
     * 修改角色信息
     */
    Boolean insertByDto(RoleDto dto);

    /**
     * 修改角色信息
     */
    Boolean updateByDto(RoleDto dto);

    /**
     * 校验并批量删除角色信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
