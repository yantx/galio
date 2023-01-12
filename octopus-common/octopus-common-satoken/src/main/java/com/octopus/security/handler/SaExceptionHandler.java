package com.octopus.security.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SameTokenInvalidException;
import cn.hutool.core.util.ObjectUtil;
import com.octopus.core.enums.ResponseCodeEnum;
import com.octopus.core.model.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: octopus
 * @createTime: 2023-01-12
 * @Description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class SaExceptionHandler {
    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public ResponseVo handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return new ResponseVo(ResponseCodeEnum.FORBIDDEN);
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public ResponseVo handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',角色权限校验失败'{}'", requestURI, e.getMessage());
        return new ResponseVo(ResponseCodeEnum.FORBIDDEN);
    }

    /**
     * 认证失败
     */
    @ExceptionHandler(NotLoginException.class)
    public ResponseVo handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',认证失败'{}',无法访问系统资源", requestURI, e.getMessage());
        return new ResponseVo(ResponseCodeEnum.UNAUTHORIZED);
    }

    /**
     * 无效认证
     */
    @ExceptionHandler(SameTokenInvalidException.class)
    public ResponseVo handleSameTokenInvalidException(SameTokenInvalidException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',内网认证失败'{}',无法访问系统资源", requestURI, e.getMessage());
        return new ResponseVo(ResponseCodeEnum.UNAUTHORIZED);
    }

}
