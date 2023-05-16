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

    SUCCESS(012000, "请求操作成功"),
    SUCCESS_DATA_NULL(012004, "请求成功,响应数据为空"),
    MEMBER_NOT_EXITS(014001, "member.not.exists"),
    MEMBER_PASSWORD_RETRY_LIMIT_COUNT(014002, "member.password.retry.limit.count"),
    MEMBER_PASSWORD_RETRY_LIMIT_EXCEED(014003, "member.password.retry.limit.exceed");

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
