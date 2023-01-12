package com.octopus.security.permission;

import cn.dev33.satoken.stp.StpInterface;
import com.octopus.security.util.LoginUtil;
import com.octopus.system.dto.LoginAccountDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: octopus
 * @createTime: 2023-01-12
 * @Description: 权限管理实现类
 */
public class SaPermissionImpl implements StpInterface {

    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginAccountDTO loginUser = LoginUtil.getLoginAccount();
        return new ArrayList<>(loginUser.getMenuPermission());
    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginAccountDTO loginUser = LoginUtil.getLoginAccount();
        return new ArrayList<>(loginUser.getRolePermission());
    }
}
