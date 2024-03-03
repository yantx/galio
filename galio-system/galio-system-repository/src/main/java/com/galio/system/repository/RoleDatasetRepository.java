package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galio.system.mapper.RoleDatasetMapper;
import com.galio.system.entity.RoleDataset;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色和数据集关联Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class RoleDatasetRepository{

    private final RoleDatasetMapper roleDatasetMapper;

    /**
     * 查询角色和数据集关联列表
     */
    public List<RoleDataset> selectList(Long roleId) {
        LambdaQueryWrapper<RoleDataset> lqw = Wrappers.lambdaQuery();
        lqw.eq(RoleDataset::getRoleId, roleId);
        return roleDatasetMapper.selectList(lqw);
    }

    /**
     * 新增角色和数据集关联
     */
    public boolean insertBatch(List<RoleDataset> entity) {
        boolean flag = roleDatasetMapper.insertBatch(entity);
        return flag;
    }

    /**
     * 删除角色和数据集关联
     */
    public int deleteRoleId(Long roleId) {
        LambdaQueryWrapper<RoleDataset> lqw = Wrappers.lambdaQuery();
        lqw.eq(RoleDataset::getRoleId, roleId);
        return roleDatasetMapper.delete(lqw);
    }

    /**
     * 批量删除角色和数据集关联
     */
    public int deleteRoleIds(Collection<Long> roleIds) {
        LambdaQueryWrapper<RoleDataset> lqw = Wrappers.lambdaQuery();
        lqw.in(RoleDataset::getRoleId, roleIds);
        return roleDatasetMapper.delete(lqw);
    }
}
