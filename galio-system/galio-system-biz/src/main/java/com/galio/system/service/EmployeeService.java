package com.galio.system.service;

import com.galio.system.dto.EmployeeDto;
import com.galio.system.model.Employee;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构Service接口
 */
public interface EmployeeService {

    /**
     * 查询机构
     */
    Employee queryById(Long employeeId);

    /**
     * 查询机构列表
     */
    Page<Employee> queryPageList(PageDto pageDto);

    /**
     * 查询机构列表
     */
    List<Employee> queryList(EmployeeDto dto);

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
