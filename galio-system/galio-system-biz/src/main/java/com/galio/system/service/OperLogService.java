package com.galio.system.service;

import com.galio.system.dto.OperLogDto;
import com.galio.system.model.OperLog;
import com.galio.mybatis.page.PageDto;
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
    OperLog queryById(Long operId);

    /**
     * 查询操作日志记录列表
     */
    Page<OperLog> queryPageList(PageDto pageDto);

    /**
     * 查询操作日志记录列表
     */
    List<OperLog> queryList(OperLogDto dto);

    /**
     * 修改操作日志记录
     */
    Boolean insertByDto(OperLogDto dto);

    /**
     * 修改操作日志记录
     */
    Boolean updateByDto(OperLogDto dto);

    /**
     * 校验并批量删除操作日志记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
