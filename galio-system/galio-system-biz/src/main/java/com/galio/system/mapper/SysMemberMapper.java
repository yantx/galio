package com.galio.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.annotation.DataColumn;
import com.galio.mybatis.annotation.DataPermission;
import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.entity.SysMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ocotpus
 * @Date: 2023-02-15
 * @Description: 会员信息 持久层
 */
public interface SysMemberMapper extends BaseMapperPlus<SysMemberMapper, SysMember, SysMember> {

    @DataPermission({
            @DataColumn(key = "orgName", value = "o.org_id"),
            @DataColumn(key = "employeeName", value = "e.employee_id")
    })
    Page<SysMember> selectPage(@Param("page") Page<SysMember> page, @Param(Constants.WRAPPER) Wrapper<SysMember> queryWrapper);

    /**
     * 根据条件分页查询用户列表
     *
     * @param queryWrapper 查询条件
     * @return 用户信息集合信息
     */
    @DataPermission({
            @DataColumn(key = "orgName", value = "o.rog_id"),
            @DataColumn(key = "employeeName", value = "e.employee_id")
    })
    List<SysMember> select(@Param(Constants.WRAPPER) Wrapper<SysMember> queryWrapper);

    /**
     * 根据条件分页查询已配用户角色列表
     *
     * @param queryWrapper 查询条件
     * @return 用户信息集合信息
     */
    @DataPermission({
            @DataColumn(key = "orgName", value = "o.rog_id"),
            @DataColumn(key = "employeeName", value = "e.employee_id")
    })
    Page<SysMember> selectAllocatedList(@Param("page") Page<SysMember> page, @Param(Constants.WRAPPER) Wrapper<SysMember> queryWrapper);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param queryWrapper 查询条件
     * @return 用户信息集合信息
     */
    @DataPermission({
            @DataColumn(key = "orgName", value = "o.rog_id"),
            @DataColumn(key = "employeeName", value = "e.employee_id")
    })
    Page<SysMember> selectUnallocatedList(@Param("page") Page<SysMember> page, @Param(Constants.WRAPPER) Wrapper<SysMember> queryWrapper);


    /**
     * 通过用户名名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysMember selectUserByUserName(String userName);

    /**
     * 通过手机号查询用户
     *
     * @param phonenumber 手机号
     * @return 用户对象信息
     */
    SysMember selectUserByPhonenumber(String phonenumber);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysMember selectUserById(Long userId);

}
