package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.GroupRole;
import com.galio.system.mapper.GroupRoleMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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
     * 查询分组和角色关联
     */
    public GroupRole selectById(Long groupId) {
        return groupRoleMapper.selectById(groupId);
    }

    /**
     * 查询分组和角色关联列表
     */
    public List<GroupRole> selectList(GroupRole groupRole,Map<String, Object> params) {
        LambdaQueryWrapper<GroupRole> lqw = buildQueryWrapper(groupRole, params);
        return groupRoleMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<GroupRole> buildQueryWrapper(GroupRole entity,Map<String, Object> params) {
        LambdaQueryWrapper<GroupRole> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增分组和角色关联
     */
    public int insert(GroupRole entity) {
        validEntityBeforeSave(entity);
        int flag = groupRoleMapper.insert(entity);
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GroupRole entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除分组和角色关联
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return groupRoleMapper.deleteBatchIds(ids);
    }
}
