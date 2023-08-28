package com.galio.auth.controller;

import com.galio.auth.dto.loginDto;
import com.galio.auth.service.AuthService;
import com.galio.core.model.BaseResponse;
import com.galio.system.dto.LoginMemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 账号认证相关接口
 */
@Tag(name = "账号认证")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    /**
     * 登录方法
     */
    @Operation(summary = "登录接口")
    @PostMapping("login")
    public LoginMemberDto login(@Validated @RequestBody loginDto form) {
         return authService.login(form.getUsername(), form.getPassword());
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
