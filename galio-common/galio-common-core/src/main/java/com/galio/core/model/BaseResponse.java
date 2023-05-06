package com.galio.core.model;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.enums.StatusCode;
import com.galio.core.utils.MessageUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: galio
 * @Date: 2022-11-30
 * @version:
 * @Description: 响应数据对象
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> {

    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private Object data;

    public static <T> BaseResponse<T> createSuccess() {
        return createResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), null);
    }
    public static <T> BaseResponse<T> createSuccess(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null);
    }
    public static <T> BaseResponse<T> createSuccessWithData(T data) {
        return createResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }
    public static <T> BaseResponse<T> createFail() {
        return createResponse(ResponseEnum.FAILED.getCode(), ResponseEnum.FAILED.getMsg(), null);
    }
    public static <T> BaseResponse<T> createFail(String msg) {
        return createResponse(ResponseEnum.FAILED.getCode(), msg, null);
    }
    public static <T> BaseResponse<T> createFail(int code, String msg) {
        return createResponse(code, msg, null);
    }
    public static <T> BaseResponse<T> createFail(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null);
    }
    public static <T> BaseResponse<T> createFailWithData(T data) {
        return createResponse(ResponseEnum.FAILED.getCode(), ResponseEnum.FAILED.getMsg(), data);
    }
    public static <T> BaseResponse<T> createWarn() {
        return createResponse(ResponseEnum.FAILED.getCode(), ResponseEnum.FAILED.getMsg(), null);
    }
    public static <T> BaseResponse<T> createWarn(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null);
    }
    public static <T> BaseResponse<T> createWarnWithData(T data) {
        return createResponse(ResponseEnum.FAILED.getCode(), ResponseEnum.FAILED.getMsg(), data);
    }
    public static <T> BaseResponse<T> createResponse(int code, String msg) {
        return createResponse(code, msg, null);
    }
    private static <T> BaseResponse<T> createResponse(int code, String msg, T data) {
        BaseResponse<T> r = new BaseResponse<>();
        r.setCode(code);
        r.setMsg(getMessage(msg));
        r.setData(data);
        return r;
    }
    private static String getMessage(String message,Object... args) {
        if (!StringUtils.isEmpty(message)) {
            message = MessageUtils.message(message, args);
        }
        return message;
    }
}
