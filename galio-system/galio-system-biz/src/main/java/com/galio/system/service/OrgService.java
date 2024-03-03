package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.OrgDTO;
import com.galio.system.entity.Org;
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
    Org getById(Long orgId);

    /**
     * 查询机构列表
     */
    Page<Org> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询机构列表
     */
    List<Org> list(OrgDTO dto);

    /**
     * 修改机构
     */
    Boolean save(OrgDTO dto);

    /**
     * 修改机构
     */
    Boolean update(OrgDTO dto);

    /**
     * 校验并批量删除机构信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
