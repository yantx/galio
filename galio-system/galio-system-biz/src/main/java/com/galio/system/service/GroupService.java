package com.galio.system.service;

import com.galio.system.dto.GroupDTO;
import com.galio.system.entity.Group;
import com.galio.core.model.PageRequestDTO;
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
    Group getById(Long groupId);

    /**
     * 查询群组信息列表
     */
    Page<Group> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询群组信息列表
     */
    List<Group> list(GroupDTO dto);

    /**
     * 修改群组信息
     */
    Boolean save(GroupDTO dto);

    /**
     * 修改群组信息
     */
    Boolean update(GroupDTO dto);

    /**
     * 校验并批量删除群组信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
