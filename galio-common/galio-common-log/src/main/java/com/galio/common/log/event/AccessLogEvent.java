package com.galio.common.log.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: galio
 * @Date: 2023-05-30 22:33:51
 * @Description: 日志处理类
 */

@Data
public class AccessLogEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录状态 0成功 1失败
     */
    private String status;

    /**
     * ip地址
     */
    private String ipaddr;

    /**
     * 提示消息
     */
    private String msg;

}
