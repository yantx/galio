package com.galio.system.model.converter;

import com.galio.system.dto.AccessLogDTO;
import com.galio.system.entity.AccessLog;
import org.mapstruct.Mapper;

/**
 * @Author: galio
 * @Date: 2023-12-01 07:47:02
 * @Description: 访问日志对象转换器
 */
@Mapper(componentModel = "spring")
public interface AccessLogConvert {

    AccessLog dtoToEntity(AccessLogDTO dto);
}
