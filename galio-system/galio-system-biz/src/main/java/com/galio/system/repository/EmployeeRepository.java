package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Employee;
import com.galio.system.mapper.EmployeeMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 机构Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EmployeeMapper employeeMapper;

    /**
     * 查询机构
     */
    public Employee selectById(Long employeeId) {
        return employeeMapper.selectById(employeeId);
    }

    /**
     * 查询机构列表
     */
    public Page<Employee> selectPage(Page page) {
        LambdaQueryWrapper<Employee> lqw = Wrappers.lambdaQuery();
        return employeeMapper.selectPage(page, lqw);
    }

    /**
     * 查询机构列表
     */
    public List<Employee> selectList(Employee employee) {
        LambdaQueryWrapper<Employee> lqw = buildQueryWrapper(employee);
        return employeeMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Employee> buildQueryWrapper(Employee entity) {
        LambdaQueryWrapper<Employee> lqw = Wrappers.lambdaQuery();
        lqw.eq(entity.getOrgId() != null, Employee::getOrgId, entity.getOrgId());
        lqw.like(StringUtil.isNotBlank(entity.getEmployeeName()), Employee::getEmployeeName, entity.getEmployeeName());
        lqw.eq(StringUtil.isNotBlank(entity.getEmployeeNo()), Employee::getEmployeeNo, entity.getEmployeeNo());
        lqw.eq(StringUtil.isNotBlank(entity.getEmail()), Employee::getEmail, entity.getEmail());
        lqw.eq(entity.getMobilePhone() != null, Employee::getMobilePhone, entity.getMobilePhone());
        lqw.eq(StringUtil.isNotBlank(entity.getFixedPhone()), Employee::getFixedPhone, entity.getFixedPhone());
        lqw.eq(StringUtil.isNotBlank(entity.getSex()), Employee::getSex, entity.getSex());
        lqw.eq(entity.getAge() != null, Employee::getAge, entity.getAge());
        lqw.eq(entity.getEntryDate() != null, Employee::getEntryDate, entity.getEntryDate());
        lqw.eq(entity.getTermDate() != null, Employee::getTermDate, entity.getTermDate());
        lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Employee::getStatus, entity.getStatus());
        lqw.eq(StringUtil.isNotBlank(entity.getDeleteFlag()), Employee::getDeleteFlag, entity.getDeleteFlag());
        lqw.eq(entity.getAppId() != null, Employee::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增机构
     */
    public int insert(Employee entity) {
        validEntityBeforeSave(entity);
        int flag = employeeMapper.insert(entity);
        return flag;
    }

    /**
     * 修改机构
     */
    public int updateById(Employee entity) {
        validEntityBeforeSave(entity);
        return employeeMapper.updateById(entity);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Employee entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除机构
     */
    public int deleteBatchIds(Collection<Long> ids) {
        return employeeMapper.deleteBatchIds(ids);
    }
}
