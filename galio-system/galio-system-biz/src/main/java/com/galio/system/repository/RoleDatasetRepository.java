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
     * 查询角色和数据集关联
     */
    public RoleDataset selectById(Long roleId) {
        return roleDatasetMapper.selectById(roleId);
    }

    /**
     * 查询角色和数据集关联列表
     */
    public List<RoleDataset> selectList(RoleDataset roleDataset,Map<String, Object> params) {
        LambdaQueryWrapper<RoleDataset> lqw = buildQueryWrapper(roleDataset, params);
        return roleDatasetMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<RoleDataset> buildQueryWrapper(RoleDataset entity,Map<String, Object> params) {
        LambdaQueryWrapper<RoleDataset> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增角色和数据集关联
     */
    public int insert(RoleDataset entity) {
        validEntityBeforeSave(entity);
        int flag = roleDatasetMapper.insert(entity);
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(RoleDataset entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除角色和数据集关联
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return roleDatasetMapper.deleteBatchIds(ids);
    }
}
