package com.octopus.core.model;

import com.octopus.core.enums.ResponseCodeEnum;
import com.octopus.core.enums.StatusCode;
import lombok.Data;

/**
 * @Author: octopus
 * @createTime: 2022-11-30
 * @version:
 * @Description: 响应数据对象
 */
@Data
public class ResponseVo {

    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private Object data;

    // 手动设置返回vo
    public ResponseVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 默认返回成功状态码，数据对象
    public ResponseVo(Object data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = ResponseCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    // 返回指定状态码，数据对象
    public ResponseVo(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    // 只返回状态码
    public ResponseVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }
}
