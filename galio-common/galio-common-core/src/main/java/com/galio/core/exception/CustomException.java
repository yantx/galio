package com.galio.core.exception;

import com.galio.core.enums.StatusCode;
import lombok.Getter;

/**
 * @Author: galio
 * @Date: 2022-12-05
 * @Description: 自定义异常基础类
 */
@Getter
public class CustomException extends AbstractBaseException {
    public CustomException(StatusCode statusCode, String msg, Throwable throwable) {
        super(statusCode, msg, throwable);
    }
    public CustomException(StatusCode statusCode, String msg) {
        this(statusCode, msg, null);
    }
    public CustomException(StatusCode statusCode) {
        this(statusCode, null, null);
    }
    public CustomException(int code, String message) {
        super(code, message, null);
    }
}
