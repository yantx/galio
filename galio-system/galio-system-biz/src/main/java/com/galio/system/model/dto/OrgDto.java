package com.galio.system.model.dto;

import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构业务对象
 */

@Data
public class OrgDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long orgId;

    /**
     * 父部门id
     */
    @NotNull(message = "父部门id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long parentId;

    /**
     * 祖级列表
     */
    @NotBlank(message = "祖级列表不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String ancestors;

    /**
     * 机构名
     */
    @NotBlank(message = "机构名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String orgName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long orderNum;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String leader;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String email;

    /**
     * 机构类别（1部门 2公司）
     */
    @NotBlank(message = "机构类别（1部门 2公司）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String category;

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
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
