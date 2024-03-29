package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 雇员对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_employee")
public class Employee extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "employee_id",type = IdType.ASSIGN_ID)
    private Long employeeId;
    /**
     * 雇员id
     */
    private Long orgId;
    /**
     * 雇员名
     */
    private String employeeName;
    /**
     * 工号
     */
    private String employeeNo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String mobilePhone;
    /**
     * 座机号码
     */
    private String fixedPhone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Long age;
    /**
     * 入职日期
     */
    private LocalDate entryDate;
    /**
     * 离职日期
     */
    private LocalDate termDate;
    /**
     * 状态1正常 0异常
     */
    private String status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleteFlag;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 备注
     */
    private String remark;

}
