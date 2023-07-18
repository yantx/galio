package com.galio.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galio.core.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 成员信息业务对象
 */

@Data
@Schema(description = "成员信息业务对象")
public class MemberDto extends BaseEntity {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long memberId;

    /**
     * 雇员id
     */
    @Schema(description = "雇员id")
    private Long employeeId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private Long appId;

    /**
     * 登陆名
     */
    @Schema(description = "登陆名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 成员类型(sys_member-pc端; app_member-app端)
     */
    @Schema(description = "成员类型(sys_member-pc端; app_member-app端)")
    private String memberType;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobileNumber;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String avatar;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    private String deleteFlag;

    /**
     * 最后登录ip
     */
    @Schema(description = "最后登录ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    /**
     * 启用时间
     */
    @Schema(description = "启用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enableDate;

    /**
     * 停用日期
     */
    @Schema(description = "停用日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disableDate;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


}
