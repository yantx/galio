package com.octopus.core.advice;

import com.octopus.core.enums.ResponseCodeEnum;
import com.octopus.core.model.ResponseVo;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: octopus
 * @createTime: 2022-12-03
 * @Description: 针对controller层统一的异常拦截
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({BindException.class})
    public ResponseVo MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResponseVo(ResponseCodeEnum.VALIDATE_ERROR);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseVo MethodArgumentNotValidExceptionHandler(NullPointerException e) {
        return new ResponseVo(ResponseCodeEnum.FAILED);
    }
}
