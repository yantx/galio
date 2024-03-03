package com.galio.system.model.converter;

import com.galio.system.dto.RoleDTO;
import com.galio.system.entity.Member;
import com.galio.system.model.vo.MemberVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-12-09 17:27:45
 * @Description: 角色对象转换器
 */
@Mapper(componentModel = "spring")
public interface RoleConvert {

    Member dtoToEntity(RoleDTO dto);
    MemberVo entityToVO(Member entity);
    List<MemberVo> entitiesToVOs(List<Member> entities);
}
