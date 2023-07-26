package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.RoleDataset;
import com.galio.system.mapper.RoleDatasetMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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
     * 批量删除角色和数据集关联
     */
    public int deleteRoleId(Long roleId) {
        LambdaQueryWrapper<RoleDataset> lqw = Wrappers.lambdaQuery();
        lqw.eq(RoleDataset::getRoleId, roleId);
        return roleDatasetMapper.delete(lqw);
    }
}
