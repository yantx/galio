package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.constant.MemberConstants;
import com.galio.core.model.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Author: galio
 * @Date: 2023-01-12
 * @Description: 角色实体类
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {

    /**
     * 角色ID
     */
    @TableId(value = "role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String roleName;

    /**
     * 角色权限
     */
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    private String roleKey;

    /**
     * 角色排序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本机构数据权限；4：本机构及以下数据权限；5：仅本人数据权限）
     */
    private String dataScope;

    /**
     * 功能树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    private Boolean functionCheckStrictly;

    /**
     * 角色状态（1正常 0停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String deleteFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @TableField(exist = false)
    private boolean flag = false;

    /**
     * 功能组
     */
    @TableField(exist = false)
    private Long[] functionIds;

    /**
     * 账号组
     */
    @TableField(exist = false)
    private Long[] groupIds;

    /**
     * 机构组（数据权限）
     */
    @TableField(exist = false)
    private Long[] orgIds;

    /**
     * 角色菜单权限
     */
    @TableField(exist = false)
    private Set<String> permissions;

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 是否管理员
     */
    public boolean isAdmin() {
        return MemberConstants.ADMIN_ROLE.equals(this.roleKey);
    }
    public boolean isSuperAdmin() {
        return MemberConstants.SUPER_ADMIN_ROLE.equals(this.roleKey);
    }

}
