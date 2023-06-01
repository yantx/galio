package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 角色信息视图对象
 */
@Data
@Schema(description = "角色信息视图对象")
public class RoleVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @Schema(description = "角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    /**
     * 功能树选择项是否关联显示 (1是 0否)
     */
    @Schema(description = "功能树选择项是否关联显示 (1是 0否)")
    private String functionCheckStrictly;

    /**
     * 机构树选择项是否关联显示 (1是 0否)
     */
    @Schema(description = "机构树选择项是否关联显示 (1是 0否)")
    private String orgCheckStrictly;

    /**
     * 状态1正常 0异常
     */
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    private String deleteFlag;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;


}
