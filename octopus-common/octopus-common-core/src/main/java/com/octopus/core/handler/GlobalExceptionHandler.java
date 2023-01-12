package com.octopus.core.handler;

import com.octopus.core.enums.ResponseCodeEnum;
import com.octopus.core.model.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Author: octopus
 * @createTime: 2022-12-03
 * @Description: 针对controller层统一的异常拦截
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BindException.class})
    public ResponseVo MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResponseVo(ResponseCodeEnum.VALIDATE_ERROR);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseVo MethodArgumentNotValidExceptionHandler(NullPointerException e) {
        return new ResponseVo(ResponseCodeEnum.NULL_POINTER);
    }

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseVo handlerNoHandlerFoundException(NoHandlerFoundException e){
        return new ResponseVo(ResponseCodeEnum.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVo handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e){
        return new ResponseVo(ResponseCodeEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseVo handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) {
        return new ResponseVo(ResponseCodeEnum.UNSUPPORTED_MEDIA_TYPE);
    }
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseVo handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return new ResponseVo(e.getMessage());
    }
    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseVo handlerException(Exception e) {
        return new ResponseVo(ResponseCodeEnum.FAILED);
    }

}
