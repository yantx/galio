package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 雇员业务对象
 */

@Data
@Schema(description = "雇员业务对象")
public class EmployeeDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long employeeId;

    /**
     * 雇员id
     */
    @NotNull(message = "雇员id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "雇员id")
    private Long orgId;

    /**
     * 雇员名
     */
    @NotBlank(message = "雇员名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "雇员名")
    private String employeeName;

    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "工号")
    private String employeeNo;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "手机号码")
    private Long mobilePhone;

    /**
     * 座机号码
     */
    @NotBlank(message = "座机号码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "座机号码")
    private String fixedPhone;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "性别")
    private String sex;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "年龄")
    private Long age;

    /**
     * 入职日期
     */
    @NotNull(message = "入职日期不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "入职日期")
    private LocalDateTime entryDate;

    /**
     * 离职日期
     */
    @NotNull(message = "离职日期不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "离职日期")
    private LocalDateTime termDate;

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
