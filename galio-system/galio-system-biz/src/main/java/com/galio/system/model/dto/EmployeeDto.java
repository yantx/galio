package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 机构业务对象
 */

@Data
public class EmployeeDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long employeeId;

    /**
     * 机构id
     */
    @NotNull(message = "机构id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long orgId;

    /**
     * 雇员名
     */
    @NotBlank(message = "雇员名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String employeeName;

    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String employeeNo;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String email;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long mobilePhone;

    /**
     * 座机号码
     */
    @NotBlank(message = "座机号码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String fixedPhone;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String sex;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long age;

    /**
     * 入职日期
     */
    @NotNull(message = "入职日期不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime entryDate;

    /**
     * 离职日期
     */
    @NotNull(message = "离职日期不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime termDate;

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
