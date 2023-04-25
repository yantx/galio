package com.galio.system.model.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构视图对象
 */
@Data
public class EmployeeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long employeeId;

    /**
     * 机构id
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
    private Long mobilePhone;

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
    private LocalDateTime entryDate;

    /**
     * 离职日期
     */
    private LocalDateTime termDate;

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
