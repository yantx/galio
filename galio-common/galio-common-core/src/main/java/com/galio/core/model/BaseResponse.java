package com.galio.core.model;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.enums.StatusCode;
import com.galio.core.utils.MessageUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author: galio
 * @Date: 2022-11-30
 * @version:
 * @Description: 响应数据对象
 */
@Schema(description="响应数据对象")
@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;	// 序列化版本号
    // 状态码
    @Schema(description = "状态码20000表示成功")
    private int code;

    // 状态信息
    @Schema(description = "提示消息")
    private String msg;

    // 返回对象
    @Schema(description = "返回数据内容")
    private Object data;

    public static <T> BaseResponse<T> createSuccess() {
        return createSuccess(ResponseEnum.SUCCESS);
    }
    public static <T> BaseResponse<T> createSuccess(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null, statusCode.getArgs());
    }
    public static <T> BaseResponse<T> createSuccessWithData(T data) {
        return createResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }
    public static <T> BaseResponse<T> createFail() {
        return createFail(ResponseEnum.FAILED);
    }
    public static <T> BaseResponse<T> createFail(String msg, Object... args) {
        return createResponse(ResponseEnum.FAILED.getCode(), msg, null, args);
    }
    public static <T> BaseResponse<T> createFail(StatusCode statusCode) {
        return createResponse(statusCode.getCode(), statusCode.getMsg(), null, statusCode.getArgs());
    }
    public static <T> BaseResponse<T> createResponse(int code, String msg) {
        return createResponse(code, msg, null);
    }
    private static <T> BaseResponse<T> createResponse(int code, String msg, T data, Object... args) {
        BaseResponse<T> r = new BaseResponse<>();
        r.setCode(code);
        r.setMsg(getMessage(msg,args));
        r.setData(data);
        return r;
    }
    private static String getMessage(String message,Object... args) {
        if (!StringUtils.isEmpty(message)) {
            message = MessageUtils.message(message, args);
        }
        return message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
