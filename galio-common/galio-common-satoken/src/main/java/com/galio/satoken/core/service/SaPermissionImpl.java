package com.galio.satoken.core.service;

import cn.dev33.satoken.stp.StpInterface;
import com.galio.satoken.utils.LoginHelper;
import com.galio.system.dto.LoginMemberDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: sa-token 权限管理实现类
 */
public class SaPermissionImpl implements StpInterface {

    /**
     * 获取功能权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginMemberDto loginMember = LoginHelper.getLoginMember();
        return new ArrayList<>(loginMember.getFunctionPermission());
    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginMemberDto loginMember = LoginHelper.getLoginMember();
        return new ArrayList<>(loginMember.getRolePermission());
    }
}