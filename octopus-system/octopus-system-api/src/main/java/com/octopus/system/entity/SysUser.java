package com.octopus.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.octopus.core.annotation.Sensitive;
import com.octopus.core.enums.SensitiveStrategy;
import com.octopus.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @Author: ocotpus
 * @createTime: 2023-01-11
 * @Description: 用户信息
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 机构ID
     */
    private Long orgId;

    /**
     * 用户账号
     */
    @Sensitive(strategy = SensitiveStrategy.USERNAME)
    private String userName;

    /**
     * 用户昵称
     */
    private String userNo;

    /**
     * 用户邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    private String email;

    /**
     * 手机号码
     */
    @Sensitive(strategy = SensitiveStrategy.MOBILE_PHONE)
    private String mobilePhone;

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
    private Integer deleteFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门对象
     */
    @TableField(exist = false)
    private SysOrg org;

    public SysUser(Long userId) {
        this.userId = userId;
    }

}
