package com.galio.system.model.converter;

import com.galio.system.dto.EmployeeDTO;
import com.galio.system.entity.Employee;
import com.galio.system.model.vo.EmployeeVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-12-09 17:19:07
 * @Description: 雇员对象转换器
 */
@Mapper(componentModel = "spring")
public interface EmployeeConvert {

    Employee dtoToEntity(EmployeeDTO dto);
    EmployeeVo entityToVO(Employee entity);
    List<EmployeeVo> entitiesToVOs(List<Employee> entities);
}
