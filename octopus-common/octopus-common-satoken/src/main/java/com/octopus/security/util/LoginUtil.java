package com.octopus.security.util;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.octopus.system.dto.LoginAccountDTO;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ocotpus
 * @createTime: 2023-01-12
 * @Description: 登录工具类
 */
public class LoginUtil {

    public static final String JOIN_CODE = ":";
    public static final String LOGIN_ACCOUNT_KEY = "loginAccount";

    /**
     * 登录系统
     *
     * @param loginAccountDTO 登录用户信息
     */
    public static void login(LoginAccountDTO loginAccountDTO) {
        SaHolder.getStorage().set(LOGIN_ACCOUNT_KEY, loginAccountDTO);
        StpUtil.login(loginAccountDTO.getLoginId());
        setLoginAccount(loginAccountDTO);
    }


    /**
     * 设置用户数据(多级缓存)
     */
    public static void setLoginAccount(LoginAccountDTO loginAccountDTO) {
        StpUtil.getTokenSession().set(LOGIN_ACCOUNT_KEY, loginAccountDTO);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static LoginAccountDTO getLoginAccount() {
        LoginAccountDTO loginAccountDTO = (LoginAccountDTO) SaHolder.getStorage().get(LOGIN_ACCOUNT_KEY);
        if (loginAccountDTO != null) {
            return loginAccountDTO;
        }
        loginAccountDTO = (LoginAccountDTO) StpUtil.getTokenSession().get(LOGIN_ACCOUNT_KEY);
        SaHolder.getStorage().set(LOGIN_ACCOUNT_KEY, loginAccountDTO);
        return loginAccountDTO;
    }

    /**
     * 获取账号id
     */
    public static Long getAccountId() {
        LoginAccountDTO loginAccountDTO = getLoginAccount();
        if (ObjectUtil.isNull(loginAccountDTO)) {
            String loginId = StpUtil.getLoginIdAsString();
            String[] strs = StringUtils.split(loginId, JOIN_CODE);
            // 账号id在总是在最后
            return Long.parseLong(strs[strs.length - 1]);
        }
        return loginAccountDTO.getAccountId();
    }

    /**
     * 获取部门ID
     */
    public static Long getOrgId() {
        return getLoginAccount().getOrgId();
    }

    /**
     * 获取登录名
     */
    public static String getLoginName() {
        return getLoginAccount().getLoginName();
    }

}
