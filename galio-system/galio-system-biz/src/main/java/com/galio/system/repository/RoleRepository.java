package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Role;
import com.galio.system.mapper.RoleMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 角色信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final RoleMapper roleMapper;

    /**
     * 查询角色信息
     */
    public Role selectById(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    /**
     * 查询角色信息列表
     */
    public Page<Role> selectPage(Page page) {
        LambdaQueryWrapper<Role> lqw = Wrappers.lambdaQuery();
        return roleMapper.selectPage(page, lqw);
    }

    /**
     * 查询角色信息列表
     */
    public List<Role> selectList(Role role) {
        LambdaQueryWrapper<Role> lqw = buildQueryWrapper(role);
        return roleMapper.selectList(lqw);
    }

    /**
     * 查询角色信息列表
     */
    public List<Role> selectList(Collection<Long> roleIds) {
        LambdaQueryWrapper<Role> lqw = Wrappers.lambdaQuery();
        lqw.in(Role::getRoleId, roleIds);
        return roleMapper.selectList(lqw);
    }

    /**
     * 查询角色信息列表
     */
    public List<Role> selectByMemberId(Long memberId) {
        return roleMapper.selectByMemberId(memberId);
    }

    private LambdaQueryWrapper<Role> buildQueryWrapper(Role entity) {
        LambdaQueryWrapper<Role> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtil.isNotBlank(entity.getRoleName()), Role::getRoleName, entity.getRoleName());
        lqw.eq(StringUtil.isNotBlank(entity.getRoleKey()), Role::getRoleKey, entity.getRoleKey());
        lqw.eq(entity.getOrderNum() != null, Role::getOrderNum, entity.getOrderNum());
        lqw.eq(StringUtil.isNotBlank(entity.getDataScope()), Role::getDataScope, entity.getDataScope());
        lqw.eq(StringUtil.isNotBlank(entity.getFunctionCheckStrictly()), Role::getFunctionCheckStrictly, entity.getFunctionCheckStrictly());
        lqw.eq(StringUtil.isNotBlank(entity.getOrgCheckStrictly()), Role::getOrgCheckStrictly, entity.getOrgCheckStrictly());
        lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Role::getStatus, entity.getStatus());
        lqw.eq(StringUtil.isNotBlank(entity.getDeleteFlag()), Role::getDeleteFlag, entity.getDeleteFlag());
        lqw.eq(entity.getAppId() != null, Role::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增角色信息
     */
    public int insert(Role entity) {
        validEntityBeforeSave(entity);
        int flag = roleMapper.insert(entity);
        return flag;
    }

    /**
     * 修改角色信息
     */
    public int updateById(Role entity) {
        validEntityBeforeSave(entity);
        return roleMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Role entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除角色信息
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return roleMapper.deleteBatchIds(ids);
    }
}
