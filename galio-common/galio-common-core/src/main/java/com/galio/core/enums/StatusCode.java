package com.galio.core.enums;

/**
 * @Author: galio
 * @Date: 2022-11-30
 * @Description: 状态码get接口
 */
public interface StatusCode {

    int getCode();
    String getMsg();

    /**
     * 国际化消息拼接参数获取
     * @return
     */
    Object[] getArgs();
    /**
     * 国际化消息拼接参数传入
     * @return
     */
    StatusCode withArgs(Object... args);


}
