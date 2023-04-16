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
 * @Description: 成员信息业务对象
 */

@Data
public class MemberDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long memberId;

    /**
     * 雇员id
     */
    @NotNull(message = "雇员id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long employeeId;

    /**
     * $column.columnComment
     */
    @NotNull(message = "$column.columnComment不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;

    /**
     * 登陆名
     */
    @NotBlank(message = "登陆名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String password;

    /**
     * 成员类型(sys_member-pc端; app_member-app端)
     */
    @NotBlank(message = "成员类型(sys_member-pc端; app_member-app端)不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String memberType;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String email;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long mobileNumber;

    /**
     * 头像地址
     */
    @NotBlank(message = "头像地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String avatar;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @NotBlank(message = "删除标志（0代表存在 1代表删除）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String deleteFlag;

    /**
     * 最后登录ip
     */
    @NotBlank(message = "最后登录ip不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String loginIp;

    /**
     * 最后登录时间
     */
    @NotNull(message = "最后登录时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime loginDate;

    /**
     * 启用时间
     */
    @NotNull(message = "启用时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime enableDate;

    /**
     * 停用日期
     */
    @NotNull(message = "停用日期不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private LocalDateTime disableDate;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
