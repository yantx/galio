package com.galio.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
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
    @Schema(description = "主键")
    private Long employeeId;

    /**
     * 雇员id
     */
    @Schema(description = "雇员id")
    private Long orgId;

    /**
     * 雇员名
     */
    @Schema(description = "雇员名")
    private String employeeName;

    /**
     * 工号
     */
    @Schema(description = "工号")
    private String employeeNo;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String mobilePhone;

    /**
     * 座机号码
     */
    @Schema(description = "座机号码")
    private String fixedPhone;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String sex;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Long age;

    /**
     * 入职日期
     */
    @Schema(description = "入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    /**
     * 离职日期
     */
    @Schema(description = "离职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate termDate;

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
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


}
