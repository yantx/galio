package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.OperLogDTO;
import com.galio.system.entity.OperLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 操作日志记录Service接口
 */
public interface OperLogService {

    /**
     * 查询操作日志记录
     */
    OperLog getById(Long operId);

    /**
     * 查询操作日志记录列表
     */
    Page<OperLog> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询操作日志记录列表
     */
    List<OperLog> list(OperLogDTO dto);

    /**
     * 修改操作日志记录
     */
    Boolean save(OperLogDTO dto);

    /**
     * 修改操作日志记录
     */
    Boolean update(OperLogDTO dto);

    /**
     * 校验并批量删除操作日志记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
