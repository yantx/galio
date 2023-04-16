package com.galio.system.service;

import com.galio.system.model.vo.GroupVo;
import com.galio.system.model.dto.GroupDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 群组信息Service接口
 */
public interface GroupService {

    /**
     * 查询群组信息
     */
    GroupVo queryById(Long groupId);

    /**
     * 查询群组信息列表
     */
    PageVo<GroupVo> queryPageList(PageDto pageDto);

    /**
     * 查询群组信息列表
     */
    List<GroupVo> queryList(GroupDto dto);

    /**
     * 修改群组信息
     */
    Boolean insertByDto(GroupDto dto);

    /**
     * 修改群组信息
     */
    Boolean updateByDto(GroupDto dto);

    /**
     * 校验并批量删除群组信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
