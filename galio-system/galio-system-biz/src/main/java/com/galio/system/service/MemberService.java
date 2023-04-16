package com.galio.system.service;

import com.galio.system.model.vo.MemberVo;
import com.galio.system.model.dto.MemberDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 成员信息Service接口
 */
public interface MemberService {

    /**
     * 查询成员信息
     */
    MemberVo queryById(Long memberId);

    /**
     * 查询成员信息列表
     */
    PageVo<MemberVo> queryPageList(PageDto pageDto);

    /**
     * 查询成员信息列表
     */
    List<MemberVo> queryList(MemberDto dto);

    /**
     * 修改成员信息
     */
    Boolean insertByDto(MemberDto dto);

    /**
     * 修改成员信息
     */
    Boolean updateByDto(MemberDto dto);

    /**
     * 校验并批量删除成员信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
