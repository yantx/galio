package com.galio.core.exception;

import com.galio.core.enums.StatusCode;
import com.galio.core.utils.MessageUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: galio
 * @Date: 2022-12-05
 * @Description: 自定义异常基础类
 */
@Getter
public class CustomException extends RuntimeException {
    private int code;
    private String msg;
    /**
     * 国际化对应的参数
     */
    private Object[] args;

    // 手动设置异常
    public CustomException(StatusCode statusCode) {
        // 状态码
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.args = statusCode.getArgs();
    }
    public CustomException(StatusCode statusCode, Object... args) {
        this.code = statusCode.getCode();
        this.args = args;
        this.msg = statusCode.getMsg();
    }

    // 自定义异常
    public CustomException(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public CustomException(int code, String message, Object... args) {
        this.code = code;
        this.args = args;
        this.msg = message;
    }

    @Override
    public String getMessage() {
        String message = this.msg;
        if (!StringUtils.isEmpty(message)) {
            message = MessageUtils.message(message, args);
        }
        return message;
    }
}
