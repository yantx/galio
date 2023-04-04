package com.galio.auth.controller;

import com.galio.auth.dto.loginDto;
import com.galio.auth.service.AuthService;
import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.model.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 账号认证相关接口
 */
@Validated
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    /**
     * 登录方法
     */
    @PostMapping("login")
    public Object login(@Validated @RequestBody loginDto form) {
        // 用户登录
        String accessToken = authService.login(form.getUsername(), form.getPassword());

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token", accessToken);
        return rspMap;
    }

    /**
     * 登出方法
     */
    @DeleteMapping("logout")
    public ResponseVo logout() {
        authService.logout();
        return ResponseVo.createSuccess();
    }

}
