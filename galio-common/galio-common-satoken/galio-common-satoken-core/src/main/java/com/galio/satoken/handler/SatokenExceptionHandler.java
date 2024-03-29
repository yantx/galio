package com.galio.satoken.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: galio
 * @Date: 2022-12-03
 * @Description: 针对controller层统一的异常拦截
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SatokenExceptionHandler {

    @ExceptionHandler({NotLoginException.class})
    public BaseResponse notLoginExceptionExceptionHandler(NotLoginException e) {
        log.error(e.getMessage(),e);
        return BaseResponse.createFail(ResponseEnum.UNAUTHORIZED.withArgs(e.getMessage()));
    }

}
