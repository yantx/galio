package com.galio.system.service;

import com.galio.system.dto.RoleDTO;
import com.galio.system.entity.Role;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息Service接口
 */
public interface RoleService {

    /**
     * 查询角色信息
     */
    Role getById(Long roleId);

    /**
     * 查询角色信息列表
     */
    Page<Role> listPage(PageRequestDTO pageRequestDTO);
    /**
     * 根据用户ID查询角色key
     */
    Set<String> queryRolePermissions(Long memberId);

    /**
     * 查询角色信息列表
     */
    List<Role> list(RoleDTO dto);

    /**
     * 修改角色信息
     */
    Boolean save(RoleDTO dto);

    /**
     * 修改角色信息
     */
    Boolean update(RoleDTO dto);

    /**
     * 校验并批量删除角色信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
