package com.galio.core.handler;

import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.CommunicationException;

/**
 * @Author: galio
 * @Date: 2022-12-03
 * @Description: 针对controller层统一的异常拦截
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BindException.class})
    public ResponseVo MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ResponseVo.createFail(ResponseCodeEnum.VALIDATE_ERROR);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseVo MethodArgumentNotValidExceptionHandler(NullPointerException e) {
        log.error(ResponseCodeEnum.NULL_POINTER.getMsg(), e);
        return ResponseVo.createFail(ResponseCodeEnum.NULL_POINTER);
    }

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseVo handlerNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request){
        log.error("访问地址{}不存在! {}", request.getRequestURI(), e);
        return ResponseVo.createFail(ResponseCodeEnum.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVo handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e){
        return ResponseVo.createFail(ResponseCodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseVo handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) {
        return ResponseVo.createFail(ResponseCodeEnum.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(CustomException.class)
    public ResponseVo handleCommunicationException(CustomException e, HttpServletRequest request) {
        return ResponseVo.createFail(e.getCode(),e.getMsg());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseVo handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return ResponseVo.createFail(ResponseCodeEnum.FAILED);
    }
    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseVo handlerException(Exception e) {
        return ResponseVo.createFail(ResponseCodeEnum.FAILED);
    }

}
