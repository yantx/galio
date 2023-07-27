package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.galio.system.model.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.MemberGroup;
import com.galio.system.mapper.MemberGroupMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员和群组关联Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class MemberGroupRepository{

    private final MemberGroupMapper memberGroupMapper;

    /**
     * 查询成员和群组关联
     */
    public List<MemberGroup> selectByMemberId(Long memberId) {
        LambdaQueryWrapper<MemberGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(MemberGroup::getMemberId, memberId);
        return memberGroupMapper.selectList(lqw);
    }

    /**
     * 新增成员和群组关联
     */
    public boolean insertBatch(List<MemberGroup> entity) {
        boolean flag = memberGroupMapper.insertBatch(entity);
        return flag;
    }

    /**
     * 删除成员和群组关联
     */
    public int deleteByMemberId(Long memberId) {
        LambdaQueryWrapper<MemberGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(MemberGroup::getMemberId, memberId);
        return memberGroupMapper.delete(lqw);
    }

    /**
     * 批量删除成员和群组关联
     */
    public int deleteByMemberIds(Collection<Long> memberId) {
        LambdaQueryWrapper<MemberGroup> lqw = Wrappers.lambdaQuery();
        lqw.in(MemberGroup::getMemberId, memberId);
        return memberGroupMapper.delete(lqw);
    }
}
