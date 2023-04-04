package com.galio.security.constant;

/**
 * @Author: galio
 * @Date: 2023-02-26 15:33:26
 * @Description: token的key常量
 */
public interface TokenConstants {

    /**
     * 令牌自定义标识
     */
    public static final String AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz";
}
