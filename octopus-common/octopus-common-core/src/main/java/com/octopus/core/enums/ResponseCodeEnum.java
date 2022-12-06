package com.octopus.core.enums;


import lombok.Getter;

/**
 * @Author: octopus
 * @createTime: 2022-11-30
 * @Description: 接口响应编码
 */
@Getter
public enum ResponseCodeEnum implements StatusCode{

    SUCCESS(200, "请求成功"),
    FAILED(500, "请求失败"),
    VALIDATE_ERROR(412, "参数校验失败"),
    RESPONSE_PACK_ERROR(50001,"response返回包装失败");

    private int code;
    private String msg;

    ResponseCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
