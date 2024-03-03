package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 成员信息对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_member")
public class Member extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "member_id",type = IdType.ASSIGN_ID)
    private Long memberId;
    /**
     * 雇员id
     */
    private Long employeeId;
    /**
     * 应用ID
     */
    private Long appId;
    /**
     * 登陆名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 成员类型(sys_member-pc端; app_member-app端)
     */
    private String memberType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobileNumber;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleteFlag;
    /**
     * 最后登录ip
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;
    /**
     * 启用时间
     */
    private LocalDateTime enableDate;
    /**
     * 停用日期
     */
    private LocalDateTime disableDate;
    /**
     * 备注
     */
    private String remark;

}
