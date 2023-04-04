package com.galio.security.handler;

import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Author: ocotpus
 * @Date: 2023-02-26 15:13:27
 * @Description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {
    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVo handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求" , requestURI, e.getMethod());
        return ResponseVo.createFail(ResponseCodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public ResponseVo handleServiceException(CustomException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResponseVo.createFail(e.getCode(),e.getMsg());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseVo handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常." , requestURI, e);
        return ResponseVo.createFail(ResponseCodeEnum.UNKNOWN_EXCEPTION);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseVo handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常." , requestURI, e);
        return ResponseVo.createFail(ResponseCodeEnum.FAILED);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseVo handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseVo.createFail(ResponseCodeEnum.FAILED);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseVo.createFail(ResponseCodeEnum.FAILED);
    }

}
