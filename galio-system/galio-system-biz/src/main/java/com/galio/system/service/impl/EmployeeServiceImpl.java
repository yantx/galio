package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
    import com.galio.mybatis.page.PageDto;
    import com.galio.mybatis.page.PageVo;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.EmployeeDto;
import com.galio.system.model.vo.EmployeeVo;
import com.galio.system.model.Employee;
import com.galio.system.mapper.EmployeeMapper;
import com.galio.system.service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 机构Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    /**
     * 查询机构
     */
    @Override
    public EmployeeVo queryById(Long employeeId) {
        return employeeMapper.selectVoById(employeeId);
    }

        /**
         * 查询机构列表
         */
        @Override
        public PageVo<EmployeeVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Employee> lqw = Wrappers.lambdaQuery();
            IPage<EmployeeVo> pageData = employeeMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询机构列表
     */
    @Override
    public List<EmployeeVo> queryList(EmployeeDto dto) {
        LambdaQueryWrapper<Employee> lqw = buildQueryWrapper(dto);
        return employeeMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Employee> buildQueryWrapper(EmployeeDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Employee> lqw = Wrappers.lambdaQuery();
                    lqw.eq(dto.getOrgId() != null, Employee::getOrgId, dto.getOrgId());
                    lqw.like(StringUtil.isNotBlank(dto.getEmployeeName()), Employee::getEmployeeName, dto.getEmployeeName());
                    lqw.eq(StringUtil.isNotBlank(dto.getEmployeeNo()), Employee::getEmployeeNo, dto.getEmployeeNo());
                    lqw.eq(StringUtil.isNotBlank(dto.getEmail()), Employee::getEmail, dto.getEmail());
                    lqw.eq(dto.getMobilePhone() != null, Employee::getMobilePhone, dto.getMobilePhone());
                    lqw.eq(StringUtil.isNotBlank(dto.getFixedPhone()), Employee::getFixedPhone, dto.getFixedPhone());
                    lqw.eq(StringUtil.isNotBlank(dto.getSex()), Employee::getSex, dto.getSex());
                    lqw.eq(dto.getAge() != null, Employee::getAge, dto.getAge());
                    lqw.eq(dto.getEntryDate() != null, Employee::getEntryDate, dto.getEntryDate());
                    lqw.eq(dto.getTermDate() != null, Employee::getTermDate, dto.getTermDate());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Employee::getStatus, dto.getStatus());
                    lqw.eq(StringUtil.isNotBlank(dto.getDeleteFlag()), Employee::getDeleteFlag, dto.getDeleteFlag());
                    lqw.eq(dto.getAppId() != null, Employee::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增机构
     */
    @Override
    public Boolean insertByDto(EmployeeDto dto) {
        Employee add = ObjectUtil.copyObject(dto, Employee. class);
        validEntityBeforeSave(add);
        boolean flag = employeeMapper.insert(add) > 0;
        if (flag) {
            dto.setEmployeeId(add.getEmployeeId());
        }
        return flag;
    }

    /**
     * 修改机构
     */
    @Override
    public Boolean updateByDto(EmployeeDto dto) {
        Employee update = ObjectUtil.copyObject(dto, Employee. class);
        validEntityBeforeSave(update);
        return employeeMapper.updateById(update) > 0;
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
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return employeeMapper.deleteBatchIds(ids) > 0;
    }
}
