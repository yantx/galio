package com.galio.auth.enums;

import com.galio.core.enums.StatusCode;
import lombok.Getter;

/**
 * @Author: galio
 * @Date: 2023-05-13 09:22:58
 * @Description: 鉴权服务响应信息枚举
 */
@Getter
public enum AuthResponseEnum implements StatusCode {

    LOGIN_SUCCESS(20000, "member.login.success"),
    MEMBER_PASSWORD_RETRY_LIMIT_COUNT(10001, "member.password.retry.limit.count"),
    MEMBER_PASSWORD_RETRY_LIMIT_EXCEED(10002, "member.password.retry.limit.exceed");


    private int code;
    private String msg;
    private Object[] args;
    AuthResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public AuthResponseEnum packageByArgs(Object... args) {
        this.args = args;
        return this;
    }

}
