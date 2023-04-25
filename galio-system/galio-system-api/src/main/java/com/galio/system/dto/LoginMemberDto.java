package com.galio.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galio.system.model.SysEmployee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: 登录账号实体
 */
@Data
@NoArgsConstructor
public class LoginMemberDto implements Serializable {

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
     * 菜单权限
     */
    private Set<String> functionPermission;

    /**
     * 角色权限
     */
    private Set<String> rolePermission;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 角色对象集
     */
    private List<RoleDto> roles;

    /**
     * 登录账号所属的人员信息
     */
    private SysEmployee employee;

    /**
     * 数据权限 当前角色ID
     */
    private Long roleId;

}
