package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
    public MemberGroup selectById(Long memberId) {
        return memberGroupMapper.selectById(memberId);
    }

    /**
     * 查询成员和群组关联列表
     */
    public List<MemberGroup> selectList(MemberGroup memberGroup,Map<String, Object> params) {
        LambdaQueryWrapper<MemberGroup> lqw = buildQueryWrapper(memberGroup, params);
        return memberGroupMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<MemberGroup> buildQueryWrapper(MemberGroup entity,Map<String, Object> params) {
        LambdaQueryWrapper<MemberGroup> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增成员和群组关联
     */
    public int insert(MemberGroup entity) {
        validEntityBeforeSave(entity);
        int flag = memberGroupMapper.insert(entity);
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberGroup entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除成员和群组关联
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return memberGroupMapper.deleteBatchIds(ids);
    }
}
