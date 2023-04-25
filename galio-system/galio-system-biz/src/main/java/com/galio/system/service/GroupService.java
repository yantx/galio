package com.galio.system.service;

import com.galio.system.model.dto.GroupDto;
import com.galio.system.model.Group;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 群组信息Service接口
 */
public interface GroupService {

    /**
     * 查询群组信息
     */
    Group queryById(Long groupId);

    /**
     * 查询群组信息列表
     */
    Page<Group> queryPageList(PageDto pageDto);

    /**
     * 查询群组信息列表
     */
    List<Group> queryList(GroupDto dto);

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
