package com.galio.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.annotation.DataColumn;
import com.galio.mybatis.annotation.DataPermission;
import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.entity.SysEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 雇员表 持久层
 */
public interface SysEmployeeMapper extends BaseMapperPlus<SysEmployeeMapper, SysEmployee, SysEmployee> {

    @DataPermission({
        @DataColumn(key = "orgName", value = "o.org_id"),
        @DataColumn(key = "employeeName", value = "e.employee_id")
    })
    Page<SysEmployee> selectPage(@Param("page") Page<SysEmployee> page, @Param(Constants.WRAPPER) Wrapper<SysEmployee> queryWrapper);

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
    List<SysEmployee> select(@Param(Constants.WRAPPER) Wrapper<SysEmployee> queryWrapper);

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
    Page<SysEmployee> selectAllocatedList(@Param("page") Page<SysEmployee> page, @Param(Constants.WRAPPER) Wrapper<SysEmployee> queryWrapper);

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
    Page<SysEmployee> selectUnallocatedList(@Param("page") Page<SysEmployee> page, @Param(Constants.WRAPPER) Wrapper<SysEmployee> queryWrapper);


    /**
     * 通过用户名名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysEmployee selectUserByUserName(String userName);

    /**
     * 通过手机号查询用户
     *
     * @param phonenumber 手机号
     * @return 用户对象信息
     */
    SysEmployee selectUserByPhonenumber(String phonenumber);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysEmployee selectUserById(Long userId);

}
