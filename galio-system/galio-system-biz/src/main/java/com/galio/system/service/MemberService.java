package com.galio.system.service;

import com.galio.system.dto.MemberDTO;
import com.galio.system.entity.Member;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.system.model.vo.MemberVo;

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
    MemberVo getById(Long memberId);
    MemberVo getByName(String username);

    /**
     * 查询成员信息列表
     */
    Page<MemberVo> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询成员信息列表
     */
    List<MemberVo> list(MemberDTO dto);

    /**
     * 修改成员信息
     */
    Boolean save(MemberDTO dto);

    /**
     * 修改成员信息
     */
    Boolean update(MemberDTO dto);

    /**
     * 校验并批量删除成员信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
