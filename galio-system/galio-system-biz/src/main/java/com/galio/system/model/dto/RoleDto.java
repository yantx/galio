package com.galio.system.model.dto;

import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息业务对象
 */

@Data
public class RoleDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String roleName;

    /**
     * 角色权限字符串
     */
    @NotBlank(message = "角色权限字符串不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String roleKey;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long orderNum;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @NotBlank(message = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String dataScope;

    /**
     * 功能树选择项是否关联显示 (1是 0否)
     */
    @NotBlank(message = "功能树选择项是否关联显示 (1是 0否)不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String functionCheckStrictly;

    /**
     * 机构树选择项是否关联显示 (1是 0否)
     */
    @NotBlank(message = "机构树选择项是否关联显示 (1是 0否)不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String orgCheckStrictly;

    /**
     * 状态1正常 0异常
     */
    @NotBlank(message = "状态1正常 0异常不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @NotBlank(message = "删除标志（0代表存在 1代表删除）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String deleteFlag;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;


}
