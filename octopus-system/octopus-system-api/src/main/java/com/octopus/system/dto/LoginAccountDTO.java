package com.octopus.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.octopus.core.constant.CacheConstant;
import com.octopus.system.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: octopus
 * @createTime: 2023-01-09
 * @Description: 登录账号实体
 */
@Data
@NoArgsConstructor
public class LoginAccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long accountId;

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
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

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
    private Set<String> menuPermission;

    /**
     * 角色权限
     */
    private Set<String> rolePermission;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 角色对象集
     */
    private List<RoleDTO> roles;

    /**
     * 登录账号所属的人员信息
     */
    private SysUser user;

    /**
     * 数据权限 当前角色ID
     */
    private Long roleId;


    /**
     * 获取登录id
     */
    public String getLoginId() {
//        if (userType == null) {
//            throw new IllegalArgumentException("用户类型不能为空");
//        }
        Assert.isNull(accountId, "账号ID不能为空");
        // TODO 前缀增加的登录的应用端 pc/app/xcx/data_view
        return  CacheConstant.LOGINID_JOIN_CODE + accountId;
    }
}
