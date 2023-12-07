package com.galio.core.enums;


import lombok.Getter;

/**
 * @Author: galio
 * @Date: 2022-11-30
 * @Description: 接口响应编码
 */
@Getter
public enum ResponseEnum implements StatusCode{

    SUCCESS(20000, "请求操作成功"),
    SUCCESS_DATA_NULL(20400, "请求成功,响应数据为空"),
    VALIDATE_ERROR(41200, "precondition.failed"),

    MEMBER_NOT_EXITS(10000, "member.not.exists"),
    ORDER_VALIDATE_ERROR(4121,"order.precondition.failed"),
    UNAUTHORIZED(40100, "unauthorized"),
    NO_PERMISSION(40300, "no.permission"),
    NO_ROLE(40301, "no.role"),
    NO_TOKEN(40302,  "no.token"),
    NO_MEMBER_ID_IN_STORAGE(40303,"no.member.id.in.storage"),
    NOT_FOUND(40400,"not.found"),
    METHOD_NOT_ALLOWED(40500,"method.not.allowed"),
    UNSUPPORTED_MEDIA_TYPE(41500,"unsupported.media.type"),

    // 服务端异常
    FAILED(50000,  "操作失败"),
    INTERNAL_SERVER_ERROR(50007,  "internal.server.error"),
    WARN(60000,  "系统告警提示"),
    NULL_POINTER(50001,"null.pointer"),
    JSON_PROCESSING_EXCEPTION(50002,"response返回包装失败"),
    SNOW_FLASK_WORKER_ID_ERROR(50003,"snow.flask.worker.id.error"),
    NOT_REQUEST_LOG_WRITE_ERROR(50004,"not.request.log.write.error"),
    BAD_GATEWAY(50200,  "bad gateway"),
    SERVICE_UNAVAILABLE(50300,  "service unavailable"),
    GATEWAY_TIMEOUT(50400,  "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(50500,  "HTTP Version not supported"),
    VARIANT_ALSO_NEGOTIATES(50600,  "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(50700,  "Insufficient Storage"),
    LOOP_DETECTED(50800,  "Loop Detected"),
    BANDWIDTH_LIMIT_EXCEEDED(50900,  "Bandwidth Limit Exceeded"),
    NOT_EXTENDED(51000,  "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(51100,  "Network Authentication Required"),
    SQL_EXCEPTION(51200,"sql.execute.error"),
    SQL_PARAM_ERROR(51201,"sql.param.error"),
    SQL_INJECTION_ERROR(51202,"sql.injection.error"),
    BEAN_INSTANTIATION_ERROR(51300,"bean.instantiation.error"),
    DATA_PERMISSION_PARSER_ERROR(51400,"data.permission.parser.error"),
    NO_ROLE_DATA_SCOPE(40304,"no.role.data.scope"),
    NO_SUCH_ALGORITHM(54400,"no.such.algorithm"),
    CLASS_NOT_FOUND(54401,  "class.not.found"),
    PARAM_NOT_NULL(54402,"param.not.null"),
    UNKNOWN_HOST(55500,  "unknown.host"),
    REMOTE_ANALYZE_RESPONSE_ERROR(50005,  "remote.analyze.response.error"),
    REMOTE_RESPONSE_ERROR(50006,  "remote.response.error"),
    SERVICE_INVOCATION_ERROR(66666,  "service.invocation.error"),
    UNKNOWN_EXCEPTION(9999,"未知异常");

    private int code;
    private String msg;

    private Object[] args;

    ResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public ResponseEnum withArgs(Object... args) {
        this.args = args;
        return this;
    }

    /**
     * 自定义响应消息
     * @param customMessage
     * @return
     */
    public ResponseEnum replaceMsg(String customMessage) {
        this.msg = customMessage;
        return this;
    }
}
