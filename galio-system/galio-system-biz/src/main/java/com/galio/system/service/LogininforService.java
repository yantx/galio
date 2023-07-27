package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.LogininforDto;
import com.galio.system.model.Logininfor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 系统访问记录Service接口
 */
public interface LogininforService {

    /**
     * 查询系统访问记录
     */
    Logininfor queryById(Long infoId);

    /**
     * 查询系统访问记录列表
     */
    Page<Logininfor> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询系统访问记录列表
     */
    List<Logininfor> queryList(LogininforDto dto);

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
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
