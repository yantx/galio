package com.galio.system.service;

import com.galio.system.model.vo.LogininforVo;
import com.galio.system.model.dto.LogininforDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 系统访问记录Service接口
 */
public interface LogininforService {

    /**
     * 查询系统访问记录
     */
    LogininforVo queryById(Long infoId);

    /**
     * 查询系统访问记录列表
     */
    PageVo<LogininforVo> queryPageList(PageDto pageDto);

    /**
     * 查询系统访问记录列表
     */
    List<LogininforVo> queryList(LogininforDto dto);

    /**
     * 修改系统访问记录
     */
    Boolean insertByDto(LogininforDto dto);

    /**
     * 修改系统访问记录
     */
    Boolean updateByDto(LogininforDto dto);

    /**
     * 校验并批量删除系统访问记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
