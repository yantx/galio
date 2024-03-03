package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galio.system.mapper.RoleFunctionMapper;
import com.galio.system.entity.RoleFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色和功能关联Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class RoleFunctionRepository{

    private final RoleFunctionMapper roleFunctionMapper;

    public List<RoleFunction> selectList(Long roleId) {
        LambdaQueryWrapper<RoleFunction> lqw = Wrappers.lambdaQuery();
        lqw.eq(RoleFunction::getRoleId, roleId);
        return roleFunctionMapper.selectList(lqw);
    }
    public List<RoleFunction> selectList(Collection<Long> roleIds) {
        LambdaQueryWrapper<RoleFunction> lqw = Wrappers.lambdaQuery();
        lqw.in(RoleFunction::getRoleId, roleIds);
        return roleFunctionMapper.selectList(lqw);
    }

    /**
     * 新增角色和功能关联
     */
    public boolean insertBatch(List<RoleFunction> entity) {
        boolean flag = roleFunctionMapper.insertBatch(entity);
        return flag;
    }

    /**
     * 删除角色和功能关联
     */
    public int deleteRoleId(Long roleId) {
        LambdaQueryWrapper<RoleFunction> lqw = Wrappers.lambdaQuery();
        lqw.eq(RoleFunction::getRoleId, roleId);
        return roleFunctionMapper.delete(lqw);
    }
    /**
     * 批量删除角色和功能关联
     */
    public int deleteRoleIds(Collection<Long> roleIds) {
        LambdaQueryWrapper<RoleFunction> lqw = Wrappers.lambdaQuery();
        lqw.in(RoleFunction::getRoleId, roleIds);
        return roleFunctionMapper.delete(lqw);
    }
}
