package com.galio.system.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.core.utils.StringUtil;
import com.galio.system.mapper.MemberMapper;
import com.galio.system.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 成员信息Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberMapper memberMapper;

    /**
     * 查询成员信息
     */
    public Member selectById(Long memberId) {
        return memberMapper.selectById(memberId);
    }
    /**
     * 查询成员信息
     */
    public Member selectOne(Member entity) {
        LambdaQueryWrapper<Member> lqw = buildQueryWrapper(entity);
        return memberMapper.selectOne(lqw);
    }
    /**
     * 查询成员信息列表
     */
    public Page<Member> selectPage(Page page) {
        LambdaQueryWrapper<Member> lqw = Wrappers.lambdaQuery();
        return memberMapper.selectPage(page, lqw);
    }

    /**
     * 查询成员信息列表
     */
    public List<Member> selectList(Member member) {
        LambdaQueryWrapper<Member> lqw = buildQueryWrapper(member);
        return memberMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Member> buildQueryWrapper(Member entity) {
        LambdaQueryWrapper<Member> lqw = Wrappers.lambdaQuery();
        lqw.eq(entity.getEmployeeId() != null, Member::getEmployeeId, entity.getEmployeeId());
        lqw.eq(entity.getAppId() != null, Member::getAppId, entity.getAppId());
        lqw.like(StringUtil.isNotBlank(entity.getUsername()), Member::getUsername, entity.getUsername());
        lqw.eq(StringUtil.isNotBlank(entity.getPassword()), Member::getPassword, entity.getPassword());
        lqw.eq(StringUtil.isNotBlank(entity.getMemberType()), Member::getMemberType, entity.getMemberType());
        lqw.eq(StringUtil.isNotBlank(entity.getEmail()), Member::getEmail, entity.getEmail());
        lqw.eq(entity.getMobileNumber() != null, Member::getMobileNumber, entity.getMobileNumber());
        lqw.eq(StringUtil.isNotBlank(entity.getAvatar()), Member::getAvatar, entity.getAvatar());
        lqw.eq(StringUtil.isNotBlank(entity.getDeleteFlag()), Member::getDeleteFlag, entity.getDeleteFlag());
        lqw.eq(StringUtil.isNotBlank(entity.getLoginIp()), Member::getLoginIp, entity.getLoginIp());
        lqw.eq(entity.getLoginDate() != null, Member::getLoginDate, entity.getLoginDate());
        lqw.eq(entity.getEnableDate() != null, Member::getEnableDate, entity.getEnableDate());
        lqw.eq(entity.getDisableDate() != null, Member::getDisableDate, entity.getDisableDate());
        return lqw;
    }

    /**
     * 新增成员信息
     */
    public int insert(Member entity) {
        validEntityBeforeSave(entity);
        int flag = memberMapper.insert(entity);
        return flag;
    }

    /**
     * 修改成员信息
     */
    public int updateById(Member entity) {
        validEntityBeforeSave(entity);
        return memberMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Member entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除成员信息
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return memberMapper.deleteBatchIds(ids);
    }
}
