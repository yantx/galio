package com.galio.core.model;

import com.galio.core.enums.ResponseCodeEnum;
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
public class ResponseVo<T> {

    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private Object data;

    public static <T> ResponseVo<T> createSuccess() {
        return createResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg(), null);
    }
    public static <T> ResponseVo<T> createSuccess(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null);
    }
    public static <T> ResponseVo<T> createSuccessWithData(T data) {
        return createResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg(), data);
    }
    public static <T> ResponseVo<T> createFail() {
        return createResponse(ResponseCodeEnum.FAILED.getCode(), ResponseCodeEnum.FAILED.getMsg(), null);
    }
    public static <T> ResponseVo<T> createFail(String msg) {
        return createResponse(ResponseCodeEnum.FAILED.getCode(), msg, null);
    }
    public static <T> ResponseVo<T> createFail(int code, String msg) {
        return createResponse(ResponseCodeEnum.FAILED.getCode(), msg, null);
    }
    public static <T> ResponseVo<T> createFail(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null);
    }
    public static <T> ResponseVo<T> createFailWithData(T data) {
        return createResponse(ResponseCodeEnum.FAILED.getCode(), ResponseCodeEnum.FAILED.getMsg(), data);
    }
    public static <T> ResponseVo<T> createWarn() {
        return createResponse(ResponseCodeEnum.FAILED.getCode(), ResponseCodeEnum.FAILED.getMsg(), null);
    }
    public static <T> ResponseVo<T> createWarn(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null);
    }
    public static <T> ResponseVo<T> createWarnWithData(T data) {
        return createResponse(ResponseCodeEnum.FAILED.getCode(), ResponseCodeEnum.FAILED.getMsg(), data);
    }
    public static <T> ResponseVo<T> createResponse(int code, String msg) {
        return createResponse(code, msg, null);
    }
    private static <T> ResponseVo<T> createResponse(int code, String msg, T data) {
        ResponseVo<T> r = new ResponseVo<>();
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
