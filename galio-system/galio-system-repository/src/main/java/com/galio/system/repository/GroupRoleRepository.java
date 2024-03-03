package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galio.system.mapper.GroupRoleMapper;
import com.galio.system.entity.GroupRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 分组和角色关联Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class GroupRoleRepository{

    private final GroupRoleMapper groupRoleMapper;

    /**
     * 查询分组和角色关联列表
     */
    public List<GroupRole> selectList(Long groupId) {
        LambdaQueryWrapper<GroupRole> lqw = Wrappers.lambdaQuery();
        lqw.eq(GroupRole::getGroupId, groupId);
        return groupRoleMapper.selectList(lqw);
    }
    /**
     * 查询分组和角色关联列表
     */
    public List<GroupRole> selectList(Collection<Long> groupIds) {
        LambdaQueryWrapper<GroupRole> lqw = Wrappers.lambdaQuery();
        lqw.in(GroupRole::getGroupId, groupIds);
        return groupRoleMapper.selectList(lqw);
    }

    /**
     * 新增分组和角色关联
     */
    public boolean insertBatch(List<GroupRole> entity) {
        boolean flag = groupRoleMapper.insertBatch(entity);
        return flag;
    }

    /**
     * 批量删除分组和角色关联
     */
    public int deleteByGroupIds(Collection<Long> groupIds) {
        LambdaQueryWrapper<GroupRole> lqw = Wrappers.lambdaQuery();
        lqw.in(GroupRole::getGroupId, groupIds);

        return groupRoleMapper.delete(lqw);
    }

    /**
     * 删除分组和角色关联
     */
    public int deleteByGroupId(Long groupId) {
        LambdaQueryWrapper<GroupRole> lqw = Wrappers.lambdaQuery();
        lqw.eq(GroupRole::getGroupId, groupId);

        return groupRoleMapper.delete(lqw);
    }
}
