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
public class OrgDTO extends BaseEntity {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long orgId;

    /**
     * 父部门id
     */
    @Schema(description = "父部门id")
    private Long parentId;

    /**
     * 祖级列表
     */
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
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private String leader;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
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
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
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
    @Schema(description = "备注")
    private String remark;


}
