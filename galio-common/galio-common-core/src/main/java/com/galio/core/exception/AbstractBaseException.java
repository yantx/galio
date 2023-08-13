package com.galio.core.exception;

import com.galio.core.enums.StatusCode;
import com.galio.core.utils.MessageUtils;
import com.galio.core.utils.StringUtil;
import lombok.Getter;

import java.util.Optional;

/**
 * @Author: galio
 * @Date: 2023-08-13 07:12:07
 * @Description: 自定义异常基类
 */
@Getter
public abstract class AbstractBaseException extends RuntimeException{

    private int code;
    private String msg;
    /**
     * 国际化对应的参数
     */
    private Object[] args;


    public AbstractBaseException(int code, String message,Throwable throwable, Object... args) {
        super(message,throwable);
        this.code = code;
        this.args = args;
        this.msg = message;
    }
    public AbstractBaseException(StatusCode statusCode, String msg, Throwable throwable) {
        this(statusCode.getCode(), Optional.ofNullable(msg).orElse(statusCode.getMsg()), throwable, statusCode.getArgs());
    }

    @Override
    public String getMessage() {
        String message = this.msg;
        if (!StringUtil.isEmpty(message)) {
            message = MessageUtils.message(message, args);
        }
        return message;
    }
}
