package com.galio.satoken.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.core.constant.MemberConstants;
import com.galio.core.enums.DeviceType;
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
 * deivce 为 设备类型 同一个用户类型 可以有 多种设备类型 例如 web,ios
 * 可以组成 用户类型与设备类型多对多的 权限灵活控制
 *
 * 多用户体系 针对 多种用户类型 但权限控制不一致
 * 可以组成 多用户类型表与多设备类型 分别控制权限
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
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param loginMember 登录用户信息
     */
    public static void loginByDevice(LoginMemberDto loginMember, DeviceType deviceType) {
        SaHolder.getStorage().set(LOGIN_MEMBER_KEY, loginMember);
        StpUtil.login(loginMember.getMemberId(), deviceType.getDevice());
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

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginMember().getUsername();
    }

    /**
     * 是否为管理员
     *
     * @param memberId 会员ID
     * @return 结果
     */
    public static boolean isAdmin(Long memberId) {
        return MemberConstants.ADMIN_ROLE.equals(memberId);
    }

    public static boolean isAdmin() {
        return isAdmin(getMemberId());
    }

}
