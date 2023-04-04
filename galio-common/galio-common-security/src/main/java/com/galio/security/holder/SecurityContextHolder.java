package com.galio.security.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.galio.core.constant.SecurityConstants;
import com.galio.core.text.Convert;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: galio
 * @Date: 2023-02-26 15:12:27
 * @Description: 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StringUtil.EMPTY : value);
    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(key, StringUtil.EMPTY));
    }

    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return ObjectUtil.cast(map.getOrDefault(key, null));
    }

    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static Long getMemberId() {
        return Convert.toLong(get(SecurityConstants.DETAILS_MEMBER_ID), 0L);
    }

    public static void setUserId(String account) {
        set(SecurityConstants.DETAILS_MEMBER_ID, account);
    }

    public static String getUserName() {
        return get(SecurityConstants.DETAILS_USERNAME);
    }

    public static void setUserName(String username) {
        set(SecurityConstants.DETAILS_USERNAME, username);
    }

    public static String getMemberKey() {
        return get(SecurityConstants.MEMBER_KEY);
    }

    public static void setUserKey(String userKey) {
        set(SecurityConstants.MEMBER_KEY, userKey);
    }

    public static String getPermission() {
        return get(SecurityConstants.ROLE_PERMISSION);
    }

    public static void setPermission(String permissions) {
        set(SecurityConstants.ROLE_PERMISSION, permissions);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
