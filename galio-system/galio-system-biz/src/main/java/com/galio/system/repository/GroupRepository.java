package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Group;
import com.galio.system.mapper.GroupMapper;

import java.util.List;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 群组信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final GroupMapper groupMapper;

    /**
     * 查询群组信息
     */
    public Group selectById(Long groupId) {
        return groupMapper.selectById(groupId);
    }

    /**
     * 查询群组信息列表
     */
    public Page<Group> selectPage(Page page) {
        LambdaQueryWrapper<Group> lqw = Wrappers.lambdaQuery();
        return groupMapper.selectPage(page, lqw);
    }

    /**
     * 查询群组信息列表
     */
    public List<Group> selectList(Group group) {
        LambdaQueryWrapper<Group> lqw = buildQueryWrapper(group);
        return groupMapper.selectList(lqw);
    }

    /**
     * 查询群组信息列表 By memberId
     */
    public List<Group> selectListByMember(Long memberId) {
        return groupMapper.selectListByMemberId(memberId);
    }

    private LambdaQueryWrapper<Group> buildQueryWrapper(Group entity) {
        LambdaQueryWrapper<Group> lqw = Wrappers.lambdaQuery();
        lqw.eq(entity.getGroupCode() != null, Group::getGroupCode, entity.getGroupCode());
        lqw.like(StringUtil.isNotBlank(entity.getGroupName()), Group::getGroupName, entity.getGroupName());
        lqw.eq(entity.getOrderNum() != null, Group::getOrderNum, entity.getOrderNum());
        lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Group::getStatus, entity.getStatus());
        lqw.eq(entity.getAppId() != null, Group::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增群组信息
     */
    public int insert(Group entity) {
        validEntityBeforeSave(entity);
        int flag = groupMapper.insert(entity);
        return flag;
    }

    /**
     * 修改群组信息
     */
    public int updateById(Group entity) {
        validEntityBeforeSave(entity);
        return groupMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Group entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除群组信息
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return groupMapper.deleteBatchIds(ids);
    }
}
