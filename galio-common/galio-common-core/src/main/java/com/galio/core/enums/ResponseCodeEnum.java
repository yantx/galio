package com.galio.core.enums;


import lombok.Getter;

/**
 * @Author: galio
 * @Date: 2022-11-30
 * @Description: 接口响应编码
 */
@Getter
public enum ResponseCodeEnum implements StatusCode{

    SUCCESS(2000, "请求成功"),

    // 客户端异常
    VALIDATE_ERROR(4412, "precondition.failed"),

    ORDER_VALIDATE_ERROR(4412, "order.precondition.failed"),
    UNAUTHORIZED(4401, "unauthorized"),
    NO_PERMISSION(4403, "no.permission"),
    NO_ROLE(4403, "no.role"),
    NO_TOKEN(4403, "no.token"),
    NO_MEMBER_ID_IN_STORAGE(5403, "no.member.id.in.storage"),
    NOT_FOUND(4404, "not.found"),
    METHOD_NOT_ALLOWED(4405, "method.not.allowed"),
    UNSUPPORTED_MEDIA_TYPE(4415, "unsupported.media.type"),

    // 服务端异常
    FAILED(5000, "请求失败,服务内部异常"),
    WARN(6000, "系统告警提示"),
    NULL_POINTER(5404,"空指针异常"),
    JSON_PROCESSING_EXCEPTION(5001,"response返回包装失败"),
    BAD_GATEWAY(5502, "bad gateway"),
    SERVICE_UNAVAILABLE(5503, "service unavailable"),
    GATEWAY_TIMEOUT(5504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(5505, "HTTP Version not supported"),
    VARIANT_ALSO_NEGOTIATES(5506, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(5507, "Insufficient Storage"),
    LOOP_DETECTED(5508, "Loop Detected"),
    BANDWIDTH_LIMIT_EXCEEDED(5509, "Bandwidth Limit Exceeded"),
    NOT_EXTENDED(5510, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(5511, "Network Authentication Required"),
    SQL_EXCEPTION(5512,"sql.execute.error"),
    SQL_PARAM_ERROR(5512,"sql.param.error"),
    SQL_INJECTION_ERROR(5512,"sql.injection.error"),
    BEAN_INSTANTIATION_ERROR(5513,"bean.instantiation.error"),
    DATA_PERMISSION_PARSER_ERROR(5514,"data.permission.parser.error"),
    NO_ROLE_DATA_SCOPE(5404,"no.role.data.scope"),
    NO_SUCH_ALGORITHM(5404, "no.such.algorithm"),
    CLASS_NOT_FOUND(5404, "class.not.found"),
    UNKNOWN_HOST(5555, "unknown.host"),
    UNKNOWN_EXCEPTION(9999,"未知异常");

    private int code;
    private String msg;

    ResponseCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
