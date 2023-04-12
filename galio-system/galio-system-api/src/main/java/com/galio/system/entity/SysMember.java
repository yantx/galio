package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.annotation.Sensitive;
import com.galio.core.enums.SensitiveStrategy;
import com.galio.core.model.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: galio
 * @Date: 2023-01-12
 * @Description: 账号实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("sys_account")
public class SysMember extends BaseEntity {

    /**
     * 账号ID
     */
    @TableId(value = "account_id")
    private Long memberId;

    /**
     * 用户ID
     */
    private Long employeeId;

    /**
     * 登录名
     */
    @NotBlank(message = "登录名不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号类型
     */
    private String accountType;

    /**
     * 邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Sensitive(strategy = SensitiveStrategy.MOBILE_PHONE)
    @Size(min = 11, max = 11, message = "手机号长度应为11个数字")
    private Integer mobilePhone;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 最后一次登录IP
     */
    private String loginIp;


    /**
     * 最后一次登录时间
     */
    private String loginTime;

    /**
     * 启用时间
     */
    private LocalDateTime enableTime;

    /**
     * 停用时间
     */
    private LocalDateTime disableTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String deleteFlag;

    /**
     * 备注
     */
    private String remark;

}
