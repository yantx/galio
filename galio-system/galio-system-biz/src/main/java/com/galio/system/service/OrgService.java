package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.OrgDto;
import com.galio.system.model.Org;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构Service接口
 */
public interface OrgService {

    /**
     * 查询机构
     */
    Org queryById(Long orgId);

    /**
     * 查询机构列表
     */
    Page<Org> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询机构列表
     */
    List<Org> queryList(OrgDto dto);

    /**
     * 修改机构
     */
    Boolean insertByDto(OrgDto dto);

    /**
     * 修改机构
     */
    Boolean updateByDto(OrgDto dto);

    /**
     * 校验并批量删除机构信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
