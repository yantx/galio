package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    /**
     * 查询角色和功能关联
     */
    public RoleFunction selectById(Long roleId) {
        return roleFunctionMapper.selectById(roleId);
    }

    /**
     * 查询角色和功能关联列表
     */
    public List<RoleFunction> selectList(RoleFunction roleFunction,Map<String, Object> params) {
        LambdaQueryWrapper<RoleFunction> lqw = buildQueryWrapper(roleFunction, params);
        return roleFunctionMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<RoleFunction> buildQueryWrapper(RoleFunction entity,Map<String, Object> params) {
        LambdaQueryWrapper<RoleFunction> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增角色和功能关联
     */
    public int insert(RoleFunction entity) {
        validEntityBeforeSave(entity);
        int flag = roleFunctionMapper.insert(entity);
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(RoleFunction entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除角色和功能关联
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return roleFunctionMapper.deleteBatchIds(ids);
    }
}
