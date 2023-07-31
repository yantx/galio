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
    public List<MemberRole> selectByMemberId(Long memberId) {
        LambdaQueryWrapper<MemberRole> lqw = Wrappers.lambdaQuery();
        lqw.eq(MemberRole::getMemberId, memberId);
        return memberRoleMapper.selectList(lqw);
    }

    /**
     * 批量新增成员和角色关联对象
     */
    public boolean insertBatch(List<MemberRole> entity) {
        return memberRoleMapper.insertBatch(entity);
    }

    /**
     * 删除成员和角色关联
     */
    public int deleteByMemberId(Long memberId) {
        LambdaQueryWrapper<MemberRole> lqw = Wrappers.lambdaQuery();
        lqw.eq(MemberRole::getMemberId, memberId);
        return memberRoleMapper.delete(lqw);
    }

    public int deleteByMemberIds(Collection<Long> memberIds) {
        LambdaQueryWrapper<MemberRole> lqw = Wrappers.lambdaQuery();
        lqw.in(MemberRole::getMemberId, memberIds);
        return memberRoleMapper.delete(lqw);
    }
}
