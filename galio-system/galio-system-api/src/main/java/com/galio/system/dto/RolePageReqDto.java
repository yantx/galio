package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import com.galio.core.validate.SelectGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色信息业务对象
 */

@Data
@Schema(description = "角色信息业务对象")
public class RolePageReqDto extends PageRequestDto {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @NotBlank(message = "角色权限字符串不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @NotBlank(message = "显示顺序不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @NotBlank(message = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    /**
     * 功能树选择项是否关联显示 (1是 0否)
     */
    @NotBlank(message = "功能树选择项是否关联显示 (1是 0否)不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "功能树选择项是否关联显示 (1是 0否)")
    private String functionCheckStrictly;

    /**
     * 机构树选择项是否关联显示 (1是 0否)
     */
    @NotBlank(message = "机构树选择项是否关联显示 (1是 0否)不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "机构树选择项是否关联显示 (1是 0否)")
    private String orgCheckStrictly;

    /**
     * 状态1正常 0异常
     */
    @NotBlank(message = "状态1正常 0异常不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @NotBlank(message = "删除标志（0代表存在 1代表删除）不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    private String deleteFlag;

    /**
     * 应用id
     */
    @NotBlank(message = "应用id不能为空", groups = { "SelectGroup.class" })
    @Schema(description = "应用id")
    private Long appId;


}
