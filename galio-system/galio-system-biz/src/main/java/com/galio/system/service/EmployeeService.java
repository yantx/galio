package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.EmployeeDTO;
import com.galio.system.entity.Employee;
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
    Employee getById(Long employeeId);

    /**
     * 查询雇员列表
     */
    Page<Employee> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询雇员列表
     */
    List<Employee> list(EmployeeDTO dto);

    /**
     * 修改雇员
     */
    Boolean save(EmployeeDTO dto);

    /**
     * 修改雇员
     */
    Boolean update(EmployeeDTO dto);

    /**
     * 校验并批量删除雇员信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
