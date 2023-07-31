package com.galio.mybatis.enums;

import com.galio.core.enums.StatusCode;
import lombok.Getter;

/**
 * @Author: galio
 * @Date: 2023-07-17 07:28:18
 * @Description: mybatis自定义响应码
 */
@Getter
public enum MybatisResponseEnum implements StatusCode {

    SET_FIELD_VALUE_ERROR(90001,"set.field.value.error"),
    ROLE_DATA_SCOPE_ERROR(90002,"role.data.scope.error");

    private int code;
    private String msg;

    private Object[] args;

    MybatisResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public MybatisResponseEnum packageByArgs(Object... args) {
        this.args = args;
        return this;
    }
}
