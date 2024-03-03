package com.galio.satoken.tools.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.core.enums.OperSideEnum;
import com.galio.system.dto.LoginMemberDTO;
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
    public static void login(LoginMemberDTO loginMember) {
        SaHolder.getStorage().set(LOGIN_MEMBER_KEY, loginMember);
        StpUtil.login(loginMember.getMemberId());
        MemberContextHelper.setLoginMember(loginMember);
    }

    /**
     * 登录系统 基于 终端类型
     * 针对相同用户体系不同终端
     *
     * @param loginMember 登录用户信息
     */
    public static void loginByDevice(LoginMemberDTO loginMember, OperSideEnum operSideEnum) {
        SaHolder.getStorage().set(LOGIN_MEMBER_KEY, loginMember);
        StpUtil.login(loginMember.getMemberId(), operSideEnum.getValue());
        MemberContextHelper.setLoginMember(loginMember);
    }



}
