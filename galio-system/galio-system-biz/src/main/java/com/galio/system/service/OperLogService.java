package com.galio.system.service;

import com.galio.system.model.vo.OperLogVo;
import com.galio.system.model.dto.OperLogDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 操作日志记录Service接口
 */
public interface OperLogService {

    /**
     * 查询操作日志记录
     */
    OperLogVo queryById(Long operId);

    /**
     * 查询操作日志记录列表
     */
    PageVo<OperLogVo> queryPageList(PageDto pageDto);

    /**
     * 查询操作日志记录列表
     */
    List<OperLogVo> queryList(OperLogDto dto);

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