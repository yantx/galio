package com.galio.auth.listener;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import com.galio.core.constant.CacheConstants;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.ServletUtils;
import com.galio.redis.util.RedisUtils;
import com.galio.satoken.tools.helper.LoginHelper;
import com.galio.system.dto.LoginMemberDto;
import com.galio.system.model.MemberOnline;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Author: galio
 * @Date: 2023-01-16
 * @Description: 账号行为监听
 */
@Slf4j
@Lazy(false)
@Component
@RequiredArgsConstructor
public class MemberActionListener implements SaTokenListener {

    private final SaTokenConfig tokenConfig;

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        String userAgentStr = ServletUtils.getRequest().getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        String ip = ServletUtils.getClientIP();
        LoginMemberDto accountDTO = LoginHelper.getLoginMember();
        MemberOnline memberOnline = new MemberOnline();
        memberOnline.setIpaddr(ip);
//        memberOnline.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        memberOnline.setBrowser(userAgent.getBrowser().getName());
        memberOnline.setOs(userAgent.getOperatingSystem().getName());
        memberOnline.setLoginTime(System.currentTimeMillis());
        memberOnline.setTokenId(tokenValue);
        memberOnline.setUsername(accountDTO.getUsername());
        if (ObjectUtil.isNotNull(accountDTO.getOrgName())) {
            memberOnline.setOrgName(accountDTO.getOrgName());
        }
        if(tokenConfig.getTimeout() == -1) {
            RedisUtils.setCacheObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, memberOnline);
        } else {
            RedisUtils.setCacheObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, memberOnline, Duration.ofSeconds(tokenConfig.getTimeout()));
        }
        log.info("member doLogin, memberId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("member doLogout, memberId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("member doLogoutByLoginId, memberId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("member doReplaced, memberId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
    }

    /**
     * 每次打开二级认证时触发
     */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
    }

    /**
     * 每次Token续期时触发
     */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
    }

}
