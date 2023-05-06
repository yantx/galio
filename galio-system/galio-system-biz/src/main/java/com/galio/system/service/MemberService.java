package com.galio.system.service;

import com.galio.system.model.dto.MemberDto;
import com.galio.system.model.Member;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 成员信息Service接口
 */
public interface MemberService {

    /**
     * 查询成员信息
     */
    Member queryById(Long memberId);
    Member queryByName(String username);

    /**
     * 查询成员信息列表
     */
    Page<Member> queryPageList(PageDto pageDto);

    /**
     * 查询成员信息列表
     */
    List<Member> queryList(MemberDto dto);

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
