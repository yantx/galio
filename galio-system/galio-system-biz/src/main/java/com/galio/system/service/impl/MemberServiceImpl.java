package com.galio.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.mybatis.page.MybatisPageConvertHelper;
import com.galio.system.entity.MemberGroup;
import com.galio.system.entity.MemberRole;
import com.galio.system.model.converter.MemberConvert;
import com.galio.system.model.vo.MemberVo;
import com.galio.system.repository.MemberGroupRepository;
import com.galio.system.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.MemberDTO;
import com.galio.system.entity.Member;
import com.galio.system.repository.MemberRepository;
import com.galio.system.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 成员信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final MemberGroupRepository memberGroupRepository;
    private final MemberConvert memberConvert;

    /**
     * 查询成员信息
     */
    @Override
    public MemberVo getById(Long memberId) {
        return memberConvert.entityToVO(memberRepository.selectById(memberId));
    }

    /**
     * 查询成员信息
     */
    @Override
    public MemberVo getByName(String username) {
        Member entity = new Member();
        entity.setUsername(username);
        return memberConvert.entityToVO(memberRepository.selectOne(entity));
    }

    /**
     * 查询成员信息列表
     */
    @Override
    public Page<MemberVo> listPage(PageRequestDTO pageRequestDTO) {
        Page<Member> page = memberRepository.selectPage(MybatisPageConvertHelper.build(pageRequestDTO));
        return (Page<MemberVo>) page.convert(memberConvert::entityToVO);
    }

    /**
     * 查询成员信息列表
     */
    @Override
    public List<MemberVo> list(MemberDTO dto) {
        Member entity = memberConvert.dtoToEntity(dto);
        List<Member> list = memberRepository.selectList(entity);
        return memberConvert.entitiesToVOs(list);
    }

    /**
     * 新增成员信息
     */
    @Override
    @Transactional
    public Boolean save(MemberDTO dto) {
        Member add = memberConvert.dtoToEntity(dto);
        add.setPassword(BCrypt.hashpw(dto.getPassword()));
        validEntityBeforeSave(add);
        boolean flag = memberRepository.insert(add) > 0;
        if (flag) {
            dto.setMemberId(dto.getMemberId());
            flag = relevanceGroupInfo(dto) && relevanceRoleInfo(dto);
        }
        return flag;
    }

    /**
     * 修改成员信息
     */
    @Override
    @Transactional
    public Boolean update(MemberDTO dto) {
        Member update = memberConvert.dtoToEntity(dto);
        validEntityBeforeSave(update);
        boolean flag = memberRepository.updateById(update) > 0;
        if (flag) {
            flag = relevanceGroupInfo(dto) && relevanceRoleInfo(dto);
        }
        return flag;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        boolean flag = memberRepository.deleteBatchIds(ids) > 0;
        if (flag) {
            memberGroupRepository.deleteByMemberIds(ids);
            memberRoleRepository.deleteByMemberIds(ids);
        }
        return flag;
    }

    public boolean relevanceGroupInfo(MemberDTO dto) {
        if (ObjectUtil.isEmpty(dto.getGroupIds())) {
            return true;
        }
        memberGroupRepository.deleteByMemberId(dto.getMemberId());
        List<MemberGroup> memberGroups = dto.getRoleIds().stream()
                .map(o -> new MemberGroup(dto.getMemberId(), o)).collect(Collectors.toList());
        return memberGroupRepository.insertBatch(memberGroups);
    }

    public boolean relevanceRoleInfo(MemberDTO dto) {
        if (ObjectUtil.isEmpty(dto.getRoleIds())) {
            return true;
        }
        memberRoleRepository.deleteByMemberId(dto.getMemberId());
        List<MemberRole> memberRoles = dto.getRoleIds().stream()
                .map(o -> new MemberRole(dto.getMemberId(), o)).collect(Collectors.toList());
        return memberRoleRepository.insertBatch(memberRoles);
    }
}
