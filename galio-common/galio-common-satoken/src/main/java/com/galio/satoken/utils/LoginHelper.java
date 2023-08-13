package com.galio.satoken.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.core.constant.MemberConstants;
import com.galio.core.enums.OperSideEnum;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
import com.galio.system.dto.LoginMemberDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: 登录鉴权助手
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String JOIN_CODE = ":";
    public static final String LOGIN_MEMBER_KEY = "loginMember";

    /**
     * 登录系统
     *
     * @param loginMember 登录用户信息
     */
    public static void login(LoginMemberDto loginMember) {
        SaHolder.getStorage().set(LOGIN_MEMBER_KEY, loginMember);
        StpUtil.login(loginMember.getMemberId());
        setLoginMember(loginMember);
    }

    /**
     * 登录系统 基于 终端类型
     * 针对相同用户体系不同终端
     *
     * @param loginMember 登录用户信息
     */
    public static void loginByDevice(LoginMemberDto loginMember, OperSideEnum operSideEnum) {
        SaHolder.getStorage().set(LOGIN_MEMBER_KEY, loginMember);
        StpUtil.login(loginMember.getMemberId(), operSideEnum.getValue());
        setLoginMember(loginMember);
    }

    /**
     * 设置用户数据(多级缓存)
     */
    public static void setLoginMember(LoginMemberDto loginMember) {
        StpUtil.getTokenSession().set(LOGIN_MEMBER_KEY, loginMember);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static LoginMemberDto getLoginMember() {
        LoginMemberDto loginMember = (LoginMemberDto) SaHolder.getStorage().get(LOGIN_MEMBER_KEY);
        if (loginMember != null) {
            return loginMember;
        }
        loginMember = (LoginMemberDto) StpUtil.getTokenSession().get(LOGIN_MEMBER_KEY);
        SaHolder.getStorage().set(LOGIN_MEMBER_KEY, loginMember);
        return loginMember;
    }

    /**
     * 获取用户id
     */
    public static Long getMemberId() {
        LoginMemberDto loginMember = getLoginMember();
        if (ObjectUtil.isNull(loginMember)) {
            String loginId = StpUtil.getLoginIdAsString();
            String[] strArr = StringUtil.split(loginId, JOIN_CODE);
            String memberId = strArr[strArr.length - 1];;
            if (StringUtil.isBlank(memberId)) {
                throw new CustomException(ResponseEnum.NO_MEMBER_ID_IN_STORAGE);
            }
            return Long.parseLong(memberId);
        }
        return loginMember.getMemberId();
    }

    /**
     * 获取组织ID
     */
    public static Long getOrgId() {
        return getLoginMember().getOrgId();
    }
    public static Long getAppId() {
        return getLoginMember().getAppId();
    }

    /**
     * 获取会员登录名
     */
    public static String getUsername() {
        return getLoginMember().getUsername();
    }

}
