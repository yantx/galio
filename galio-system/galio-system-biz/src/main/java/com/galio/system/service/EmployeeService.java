package com.galio.system.service;

import com.galio.system.model.vo.EmployeeVo;
import com.galio.system.model.dto.EmployeeDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 机构Service接口
 */
public interface EmployeeService {

    /**
     * 查询机构
     */
    EmployeeVo queryById(Long employeeId);

    /**
     * 查询机构列表
     */
    PageVo<EmployeeVo> queryPageList(PageDto pageDto);

    /**
     * 查询机构列表
     */
    List<EmployeeVo> queryList(EmployeeDto dto);

    /**
     * 修改机构
     */
    Boolean insertByDto(EmployeeDto dto);

    /**
     * 修改机构
     */
    Boolean updateByDto(EmployeeDto dto);

    /**
     * 校验并批量删除机构信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
