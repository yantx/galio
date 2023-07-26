package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galio.system.model.RoleDataset;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.RoleFunction;
import com.galio.system.mapper.RoleFunctionMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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

    /**
     * 新增角色和功能关联
     */
    public boolean insertBatch(List<RoleFunction> entity) {
        boolean flag = roleFunctionMapper.insertBatch(entity);
        return flag;
    }

    /**
     * 批量删除角色和功能关联
     */
    public int deleteRoleId(Long roleId) {
        LambdaQueryWrapper<RoleFunction> lqw = Wrappers.lambdaQuery();
        lqw.eq(RoleFunction::getRoleId, roleId);
        return roleFunctionMapper.delete(lqw);
    }
}
