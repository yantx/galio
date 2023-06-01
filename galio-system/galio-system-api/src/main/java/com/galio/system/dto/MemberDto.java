package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long memberId;

    /**
     * 雇员id
     */
    @NotNull(message = "雇员id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "雇员id")
    private Long employeeId;

    /**
     * $column.columnComment
     */
    @NotNull(message = "$column.columnComment不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "$column.columnComment")
    private Long appId;

    /**
     * 登陆名
     */
    @NotBlank(message = "登陆名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "登陆名")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "密码")
    private String password;

    /**
     * 成员类型(sys_member-pc端; app_member-app端)
     */
    @NotBlank(message = "成员类型(sys_member-pc端; app_member-app端)不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "成员类型(sys_member-pc端; app_member-app端)")
    private String memberType;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "手机号")
    private Long mobileNumber;

    /**
     * 头像地址
     */
    @NotBlank(message = "头像地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "头像地址")
    private String avatar;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @NotBlank(message = "删除标志（0代表存在 1代表删除）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    private String deleteFlag;

    /**
     * 最后登录ip
     */
    @NotBlank(message = "最后登录ip不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "最后登录ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @NotNull(message = "最后登录时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    /**
     * 启用时间
     */
    @NotNull(message = "启用时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "启用时间")
    private LocalDateTime enableDate;

    /**
     * 停用日期
     */
    @NotNull(message = "停用日期不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "停用日期")
    private LocalDateTime disableDate;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;


}
