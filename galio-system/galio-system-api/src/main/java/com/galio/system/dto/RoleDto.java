package com.galio.system.dto;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: 角色实体
 */
@Data
public class RoleDto {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    private String dataScope;
}
