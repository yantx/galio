package com.galio.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: galio
 * @Date: 2023-01-16
 * @Description: 在线账号
 */
@Data
public class MemberOnline implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private Long loginTime;

}
