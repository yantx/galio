package com.galio.auth.controller;

import com.galio.auth.dto.LoginDTO;
import com.galio.auth.service.AuthService;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseResponse;
import com.galio.system.dto.LoginMemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 账号认证相关接口
 */
@Tag(name = "账号认证")
@Validated
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 获取公钥
     * @return 公钥
     * @throws Exception
     */
    @GetMapping("getPublicKey")
    public String getPublicKey() throws Exception {
        return authService.getPublicKey();
    }

    /**
     * 登录接口
     * @param loginDTO 登录表单对象  username-登录名 password-登录密码
     * @return LoginMemberDTO对象
     * @throws CustomException
     */
    @Operation(summary = "登录接口")
    @PostMapping("login")
    public LoginMemberDTO login(@Validated @RequestBody LoginDTO loginDTO) {
         return authService.login(loginDTO.getUsername(), loginDTO.getPassword(),loginDTO.getSecurityKey());
    }
    @Operation(summary = "查询用户token")
    @GetMapping("token/{username}")
    public String getToken(@Validated @PathVariable("username") String username) {
        return authService.getToken(username);
    }
    /**
     * 登出方法
     */
    @Operation(summary = "退出登录")
    @GetMapping("logout")
    public BaseResponse logout() {
        authService.logout();
        return BaseResponse.createSuccess();
    }

}
