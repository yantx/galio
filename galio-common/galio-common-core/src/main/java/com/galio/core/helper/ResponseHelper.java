package com.galio.core.helper;

import com.galio.core.enums.ResponseEnum;
import com.galio.core.model.BaseResponse;

/**
 * @Author: galio
 * @Date: 2023-08-19 08:31:08
 * @Description: 响应体工具类
 */
public class ResponseHelper {

    public static boolean isSuccess(BaseResponse data) {
        return data.getCode() == ResponseEnum.SUCCESS.getCode();
    }


}
