package com.galio.system.service.impl;

import com.galio.core.utils.StringUtil;
import com.galio.core.utils.ObjectUtil;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.MemberDto;
import com.galio.system.model.Member;
import com.galio.system.repository.MemberRepository;
import com.galio.system.service.MemberService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 成员信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 查询成员信息
     */
    @Override
    public Member queryById(Long memberId) {
        return memberRepository.selectById(memberId);
    }

    /**
     * 查询成员信息
     */
    @Override
    public Member queryByName(String username) {
        Member entity = new Member();
        entity.setUsername(username);
        return memberRepository.selectOne(entity);
    }

        /**
         * 查询成员信息列表
         */
        @Override
        public Page<Member> queryPageList(PageDto pageDto) {
            return memberRepository.selectPage(pageDto.build());
        }

    /**
     * 查询成员信息列表
     */
    @Override
    public List<Member> queryList(MemberDto dto) {
        Member entity = ObjectUtil.copyObject(dto, Member.class);
        Map<String, Object> params = dto.getParams();
        return memberRepository.selectList(entity,params);
    }

    /**
     * 新增成员信息
     */
    @Override
    public Boolean insertByDto(MemberDto dto) {
        Member add = ObjectUtil.copyObject(dto, Member.class);
        validEntityBeforeSave(add);
        boolean flag = memberRepository.insert(add) > 0;
        if (flag) {
            dto.setMemberId(add.getMemberId());
        }
        return flag;
    }

    /**
     * 修改成员信息
     */
    @Override
    public Boolean updateByDto(MemberDto dto) {
        Member update = ObjectUtil.copyObject(dto, Member.class);
        validEntityBeforeSave(update);
        return memberRepository.updateById(update) > 0;
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
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return memberRepository.deleteBatchIds(ids) > 0;
    }
}
