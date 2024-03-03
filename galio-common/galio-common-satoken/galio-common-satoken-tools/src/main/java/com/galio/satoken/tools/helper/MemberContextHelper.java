package com.galio.satoken.tools.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
import com.galio.system.dto.LoginMemberDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-12-10 17:50:40
 * @Description: 用户相关信息获取助手类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberContextHelper {

    public static final String JOIN_CODE = ":";

    /**
     * 设置用户数据(多级缓存)
     */
    public static void setLoginMember(LoginMemberDTO loginMember) {
        StpUtil.getTokenSession().set(LoginHelper.LOGIN_MEMBER_KEY, loginMember);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static LoginMemberDTO getLoginMember() {
        LoginMemberDTO loginMember = (LoginMemberDTO) SaHolder.getStorage().get(LoginHelper.LOGIN_MEMBER_KEY);
        if (loginMember != null) {
            return loginMember;
        }
        loginMember = (LoginMemberDTO) StpUtil.getTokenSession().get(LoginHelper.LOGIN_MEMBER_KEY);
        SaHolder.getStorage().set(LoginHelper.LOGIN_MEMBER_KEY, loginMember);
        return loginMember;
    }

    /**
     * 获取用户id
     */
    public static Long getMemberId() {
        LoginMemberDTO loginMember = getLoginMember();
        if (ObjectUtil.isNull(loginMember)) {
            String loginId = StpUtil.getLoginIdAsString();
            String[] strArr = StringUtil.split(loginId, JOIN_CODE);
            String memberId = strArr[strArr.length - 1];
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
