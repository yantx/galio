package com.galio.system.service;

import com.galio.system.model.vo.OrgVo;
import com.galio.system.model.dto.OrgDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 机构Service接口
 */
public interface OrgService {

    /**
     * 查询机构
     */
    OrgVo queryById(Long orgId);

    /**
     * 查询机构列表
     */
    PageVo<OrgVo> queryPageList(PageDto pageDto);

    /**
     * 查询机构列表
     */
    List<OrgVo> queryList(OrgDto dto);

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
