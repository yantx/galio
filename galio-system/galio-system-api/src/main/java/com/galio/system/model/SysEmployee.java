package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import com.galio.core.annotation.Sensitive;
import com.galio.core.enums.SensitiveStrategy;
import com.galio.core.model.BaseEntity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @Author: ocotpus
 * @Date: 2023-01-11
 * @Description: 雇员信息
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_employee")
public class SysEmployee extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "employee_id")
    private Long employeeId;

    /**
     * 机构ID
     */
    private Long orgId;

    /**
     * 姓名
     */
    @Sensitive(strategy = SensitiveStrategy.USERNAME)
    private String employeeName;

    /**
     * 邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /**
     * 手机号码
     */
    @Sensitive(strategy = SensitiveStrategy.MOBILE_PHONE)
    @Size(min = 11, max = 11, message = "手机号长度应为11个数字")
    private Integer mobilePhone;

    /**
     * 座机号码
     */
    @Sensitive(strategy = SensitiveStrategy.FIXED_PHONE)
    private String fixedPhone;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 离职日期
     */
    private Date termDate;

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
     * 部门对象
     */
    @TableField(exist = false)
    private SysOrg org;

    public SysEmployee(Long employeeId) {
        this.employeeId = employeeId;
    }

}
