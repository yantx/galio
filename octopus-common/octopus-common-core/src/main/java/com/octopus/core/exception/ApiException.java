package com.octopus.core.exception;

import com.octopus.core.enums.StatusCode;
import lombok.Getter;

/**
 * @Author: octopus
 * @createTime: 2022-12-05
 * @Description: 自定义异常基础类
 */
@Getter
public class ApiException extends RuntimeException {
    private int code;
    private String msg;

    // 手动设置异常
    public ApiException(StatusCode statusCode) {
        // 状态码
        this.code = statusCode.getCode();
        // 状态码配套的msg
        this.msg = statusCode.getMsg();
    }

    // 自定义异常
    public ApiException(int code, String message) {
        this.code = code;
        this.msg = message;
    }
}
