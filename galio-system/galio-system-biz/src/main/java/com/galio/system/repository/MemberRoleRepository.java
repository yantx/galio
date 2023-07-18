package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.MemberRole;
import com.galio.system.mapper.MemberRoleMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员和角色关联Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class MemberRoleRepository{

    private final MemberRoleMapper memberRoleMapper;

    /**
     * 查询成员和角色关联
     */
    public MemberRole selectById(Long roleId) {
        return memberRoleMapper.selectById(roleId);
    }

    /**
     * 查询成员和角色关联列表
     */
    public List<MemberRole> selectList(MemberRole memberRole,Map<String, Object> params) {
        LambdaQueryWrapper<MemberRole> lqw = buildQueryWrapper(memberRole, params);
        return memberRoleMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<MemberRole> buildQueryWrapper(MemberRole entity,Map<String, Object> params) {
        LambdaQueryWrapper<MemberRole> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增成员和角色关联
     */
    public int insert(MemberRole entity) {
        validEntityBeforeSave(entity);
        int flag = memberRoleMapper.insert(entity);
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberRole entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除成员和角色关联
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return memberRoleMapper.deleteBatchIds(ids);
    }
}
