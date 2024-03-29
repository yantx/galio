package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.AccessLogDTO;
import com.galio.system.entity.AccessLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录Service接口
 */
public interface AccessLogService {

    /**
     * 查询系统访问记录
     */
    AccessLog getById(Long accessId);

    /**
     * 查询系统访问记录列表
     */
    Page<AccessLog> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询系统访问记录列表
     */
    List<AccessLog> list(AccessLogDTO dto);

    /**
     * 修改系统访问记录
     */
    Boolean save(AccessLogDTO dto);

    /**
     * 修改系统访问记录
     */
    Boolean update(AccessLogDTO dto);

    /**
     * 校验并批量删除系统访问记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
