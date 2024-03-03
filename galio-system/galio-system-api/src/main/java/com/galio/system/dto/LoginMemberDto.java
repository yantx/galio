package com.galio.system.dto;

import com.galio.core.constant.MemberConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: 登录账号业务对象
 */
@Data
@NoArgsConstructor
public class LoginMemberDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号ID
     */
    private Long memberId;

    /**
     * 机构ID
     */
    private Long orgId;

    /**
     * 机构名
     */
    private String orgName;
    private String parentOrgName;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限标识
     */
    private Set<String> functionPerms;

    /**
     * 角色标识
     */
    private Set<String> rolePerms;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色对象集
     */
    private List<RoleDTO> roles;

    /**
     * 登录账号所属的人员信息
     */
    private EmployeeDTO employee;

    private Long appId;

    public boolean isSuperAdmin(){
        return roles.stream().filter(o -> o.getRoleKey().equals(MemberConstants.SUPER_ADMIN_ROLE)).findAny().isEmpty();
    }

    public boolean isAdmin(){
        return roles.stream().filter(o -> o.getRoleKey().equals(MemberConstants.ADMIN_ROLE)).findAny().isEmpty();
    }

}
