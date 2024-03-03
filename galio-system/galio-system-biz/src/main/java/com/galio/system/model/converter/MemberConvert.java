package com.galio.system.model.converter;

import com.galio.system.dto.MemberDTO;
import com.galio.system.entity.Member;
import com.galio.system.model.vo.MemberVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-12-01 22:12:51
 * @Description: 会员信息转换器
 */
@Mapper(componentModel = "spring")
public interface MemberConvert {

    Member dtoToEntity(MemberDTO dto);
    MemberVo entityToVO(Member entity);
    List<MemberVo> entitiesToVOs(List<Member> entities);


}
