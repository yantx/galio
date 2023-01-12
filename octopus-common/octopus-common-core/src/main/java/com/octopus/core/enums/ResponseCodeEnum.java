package com.octopus.core.enums;


import lombok.Getter;

/**
 * @Author: octopus
 * @createTime: 2022-11-30
 * @Description: 接口响应编码
 */
@Getter
public enum ResponseCodeEnum implements StatusCode{

    SUCCESS(2000, "请求成功"),

    // 客户端异常
    VALIDATE_ERROR(4412, "参数校验失败"),
    UNAUTHORIZED(4401, "权限认证失败，无法访问系统资源"),
    FORBIDDEN(4403, "没有访问权限，请联系管理员授权"),
    NOT_FOUND(4404, "所请求的资源不存在、已被删除或无法访问"),
    METHOD_NOT_ALLOWED(4405, "方法不被允许，提交方法的类型错误"),
    UNSUPPORTED_MEDIA_TYPE(4415, "不支持的媒体类型"),

    // 服务端异常
    FAILED(5000, "请求失败"),
    NULL_POINTER(5404,"空指针异常"),
    JSON_PROCESSING_EXCEPTION(5001,"response返回包装失败"),
    UNKNOWN_EXCEPTION(9999,"未知异常");

    private int code;
    private String msg;

    ResponseCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
