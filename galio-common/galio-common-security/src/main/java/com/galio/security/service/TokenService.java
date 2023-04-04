package com.galio.security.service;

import com.galio.core.constant.CacheConstants;
import com.galio.core.constant.SecurityConstants;
import com.galio.core.utils.IpUtils;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.ServletUtils;
import com.galio.core.utils.StringUtil;
import com.galio.core.utils.id.IdUtils;
import com.galio.redis.util.RedisUtils;
import com.galio.security.utils.JwtUtils;
import com.galio.security.utils.SecurityUtils;
import com.galio.system.dto.LoginMemberDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: galio
 * @Date: 2023-02-26
 * @Description: token验证处理
 */
@Component
public class TokenService {

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginMemberDto loginMember) {
        String token = IdUtils.fastUUID();
        Long memberId = loginMember.getMemberId();
        String userName = loginMember.getUsername();
        loginMember.setToken(token);
        loginMember.setMemberId(memberId);
        loginMember.setUsername(userName);
        loginMember.setIpaddr(IpUtils.getIpAddr());
        refreshToken(loginMember);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.MEMBER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_MEMBER_ID, memberId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token" , JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in" , expireTime);
        return rspMap;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginMemberDto getLoginMember() {
        return getLoginMember(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginMemberDto getLoginMember(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        return getLoginMember(token);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginMemberDto getLoginMember(String token) {
        LoginMemberDto memberDto = null;
        try {
            if (StringUtil.isNotEmpty(token)) {
                String memberKey = JwtUtils.getMemberKey(token);
                memberDto = RedisUtils.getCacheObject(getTokenKey(memberKey));
                return memberDto;
            }
        } catch (Exception e) {
        }
        return memberDto;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginMemberDto(LoginMemberDto loginMember) {
        if (ObjectUtil.isNotNull(loginMember) && StringUtil.isNotEmpty(loginMember.getToken())) {
            refreshToken(loginMember);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginMemberDto(String token) {
        if (StringUtil.isNotEmpty(token)) {
            String userkey = JwtUtils.getMemberKey(token);
            RedisUtils.deleteObject(getTokenKey(userkey));
        }
    }

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginMember
     */
    public void verifyToken(LoginMemberDto loginMember) {
        long expireTime = loginMember.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginMember);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginMember 登录信息
     */
    public void refreshToken(LoginMemberDto loginMember) {
        loginMember.setLoginTime(System.currentTimeMillis());
        loginMember.setExpireTime(loginMember.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginMember缓存
        String userKey = getTokenKey(loginMember.getToken());
        RedisUtils.setCacheObject(userKey, loginMember, Duration.ofSeconds(expireTime));
    }

    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }
}