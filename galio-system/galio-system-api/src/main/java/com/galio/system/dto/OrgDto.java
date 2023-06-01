package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 机构业务对象
 */

@Data
@Schema(description = "机构业务对象")
public class OrgDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long orgId;

    /**
     * 父部门id
     */
    @NotNull(message = "父部门id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "父部门id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @NotBlank(message = "祖级列表不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "祖级列表")
    private String ancestors;

    /**
     * 机构名
     */
    @NotBlank(message = "机构名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "机构名")
    private String orgName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "负责人")
    private String leader;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "邮箱")
    private String email;

    /**
     * 机构类别（1部门 2公司）
     */
    @NotBlank(message = "机构类别（1部门 2公司）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "机构类别（1部门 2公司）")
    private String category;

    /**
     * 状态1正常 0异常
     */
    @NotBlank(message = "状态1正常 0异常不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @NotBlank(message = "删除标志（0代表存在 1代表删除）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    private String deleteFlag;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;


}
