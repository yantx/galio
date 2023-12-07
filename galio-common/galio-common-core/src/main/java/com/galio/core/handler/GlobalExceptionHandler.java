package com.galio.core.handler;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.BaseResponse;
import com.galio.core.utils.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Optional;

/**
 * @Author: galio
 * @Date: 2022-12-03
 * @Description: 针对controller层统一的异常拦截
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnClass(jakarta.servlet.ServletException.class)
public class GlobalExceptionHandler {

    @ExceptionHandler({BindException.class})
    public BaseResponse MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.error(objectError.getDefaultMessage());
        return BaseResponse.createFail(ResponseEnum.VALIDATE_ERROR.withArgs(objectError.getDefaultMessage()));
    }

    @ExceptionHandler({NullPointerException.class})
    public BaseResponse MethodArgumentNotValidExceptionHandler(NullPointerException e) {
        log.error(ResponseEnum.NULL_POINTER.getMsg(), e);
        return BaseResponse.createFail(ResponseEnum.NULL_POINTER.withArgs(e.toString()));
    }

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public BaseResponse handlerNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request){
        log.error("访问地址{}不存在! {}", request.getRequestURI(), e);
        return BaseResponse.createFail(ResponseEnum.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e){
        return BaseResponse.createFail(ResponseEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) {
        return BaseResponse.createFail(ResponseEnum.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(CustomException.class)
    public BaseResponse handleCommunicationException(CustomException e, HttpServletRequest request) {
        log.error(e.getMessage(),e);
        return BaseResponse.createResponse(e.getCode(),e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String ex =StringUtil.isNotEmpty(e.getMessage())? e.toString() : e.toString() +": " + e.getCause();
        log.error("[{}] URL:{} [ex] ", request.getMethod(), request.getRequestURI(), e);
        return BaseResponse.createFail(ResponseEnum.INTERNAL_SERVER_ERROR.withArgs(ex));
    }
    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse handlerException(Exception e, HttpServletRequest request) {
        String ex =StringUtil.isNotEmpty(e.getMessage())? e.toString() : e.toString() +": " + e.getCause();
        log.error("[{}] URL:{} [ex] ", request.getMethod(), request.getRequestURI(), e);
        return BaseResponse.createFail(ResponseEnum.INTERNAL_SERVER_ERROR.withArgs(ex));
    }

}
