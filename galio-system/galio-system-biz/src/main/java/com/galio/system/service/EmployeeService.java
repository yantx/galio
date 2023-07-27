package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.EmployeeDto;
import com.galio.system.model.Employee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 雇员Service接口
 */
public interface EmployeeService {

    /**
     * 查询雇员
     */
    Employee queryById(Long employeeId);

    /**
     * 查询雇员列表
     */
    Page<Employee> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询雇员列表
     */
    List<Employee> queryList(EmployeeDto dto);

    /**
     * 修改雇员
     */
    Boolean insertByDto(EmployeeDto dto);

    /**
     * 修改雇员
     */
    Boolean updateByDto(EmployeeDto dto);

    /**
     * 校验并批量删除雇员信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
