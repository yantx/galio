package com.galio.core.constant;

/**
 * @Author: ocotpus
 * @Date: 2023-02-26 15:06:33
 * @Description: 认证鉴权相关枚举
 */
public interface SecurityConstants {
    /**
     * 用户ID字段
     */
    public static final String DETAILS_MEMBER_ID = "member_id";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "username";

    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION_HEADER = "authorization";

    /**
     * 请求来源
     */
    public static final String FROM_SOURCE = "from-source";

    /**
     * 内部请求
     */
    public static final String INNER = "inner";

    /**
     * 会员标识
     */
    public static final String MEMBER_KEY = "member_key";

    /**
     * 登录会员
     */
    public static final String LOGIN_MEMBER = "login_member";

    /**
     * 角色权限
     */
    public static final String ROLE_PERMISSION = "role_permission";
}
