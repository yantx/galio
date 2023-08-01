package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.AccessLogDto;
import com.galio.system.model.AccessLog;
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
    AccessLog queryById(Long accessId);

    /**
     * 查询系统访问记录列表
     */
    Page<AccessLog> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询系统访问记录列表
     */
    List<AccessLog> queryList(AccessLogDto dto);

    /**
     * 修改系统访问记录
     */
    Boolean insertByDto(AccessLogDto dto);

    /**
     * 修改系统访问记录
     */
    Boolean updateByDto(AccessLogDto dto);

    /**
     * 校验并批量删除系统访问记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
