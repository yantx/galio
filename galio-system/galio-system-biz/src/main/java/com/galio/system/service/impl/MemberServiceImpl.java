package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
    import com.galio.mybatis.page.PageDto;
    import com.galio.mybatis.page.PageVo;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.MemberDto;
import com.galio.system.model.vo.MemberVo;
import com.galio.system.model.Member;
import com.galio.system.mapper.MemberMapper;
import com.galio.system.service.MemberService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 成员信息Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    /**
     * 查询成员信息
     */
    @Override
    public MemberVo queryById(Long memberId) {
        return memberMapper.selectVoById(memberId);
    }

        /**
         * 查询成员信息列表
         */
        @Override
        public PageVo<MemberVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Member> lqw = Wrappers.lambdaQuery();
            IPage<MemberVo> pageData = memberMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询成员信息列表
     */
    @Override
    public List<MemberVo> queryList(MemberDto dto) {
        LambdaQueryWrapper<Member> lqw = buildQueryWrapper(dto);
        return memberMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Member> buildQueryWrapper(MemberDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Member> lqw = Wrappers.lambdaQuery();
                    lqw.eq(dto.getEmployeeId() != null, Member::getEmployeeId, dto.getEmployeeId());
                    lqw.eq(dto.getAppId() != null, Member::getAppId, dto.getAppId());
                    lqw.like(StringUtil.isNotBlank(dto.getUsername()), Member::getUsername, dto.getUsername());
                    lqw.eq(StringUtil.isNotBlank(dto.getPassword()), Member::getPassword, dto.getPassword());
                    lqw.eq(StringUtil.isNotBlank(dto.getMemberType()), Member::getMemberType, dto.getMemberType());
                    lqw.eq(StringUtil.isNotBlank(dto.getEmail()), Member::getEmail, dto.getEmail());
                    lqw.eq(dto.getMobileNumber() != null, Member::getMobileNumber, dto.getMobileNumber());
                    lqw.eq(StringUtil.isNotBlank(dto.getAvatar()), Member::getAvatar, dto.getAvatar());
                    lqw.eq(StringUtil.isNotBlank(dto.getDeleteFlag()), Member::getDeleteFlag, dto.getDeleteFlag());
                    lqw.eq(StringUtil.isNotBlank(dto.getLoginIp()), Member::getLoginIp, dto.getLoginIp());
                    lqw.eq(dto.getLoginDate() != null, Member::getLoginDate, dto.getLoginDate());
                    lqw.eq(dto.getEnableDate() != null, Member::getEnableDate, dto.getEnableDate());
                    lqw.eq(dto.getDisableDate() != null, Member::getDisableDate, dto.getDisableDate());
        return lqw;
    }

    /**
     * 新增成员信息
     */
    @Override
    public Boolean insertByDto(MemberDto dto) {
        Member add = ObjectUtil.copyObject(dto, Member. class);
        validEntityBeforeSave(add);
        boolean flag = memberMapper.insert(add) > 0;
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
        Member update = ObjectUtil.copyObject(dto, Member. class);
        validEntityBeforeSave(update);
        return memberMapper.updateById(update) > 0;
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
        return memberMapper.deleteBatchIds(ids) > 0;
    }
}
