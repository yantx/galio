package com.galio.auth.dto;

import com.galio.core.constant.MemberConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: galio
 * @Date: 2023-01-16
 * @Description: 登录表单参数
 */
@Data
@Schema(description = "登录参数")
public class LoginDTO {
    /**
     * 登录名
     */
    @Schema(description = "登录名")
    @NotBlank(message = "{member.username.not.blank}")
    @Length(min = MemberConstants.USERNAME_MIN_LENGTH, max = MemberConstants.USERNAME_MAX_LENGTH, message = "{member.username.length.valid}")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "登录密码")
    @NotBlank(message = "{member.password.not.blank}")
    private String password;

    /**
     * 安全口令 经过非对称加密后的对称加密密钥
     */
    @Schema(description = "安全口令")
    @NotBlank(message = "{member.key.not.blank}")
    private String securityKey;

}
