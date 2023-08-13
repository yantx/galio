package com.galio.system.mapper;

import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.model.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 群组信息Mapper接口
 */
public interface GroupMapper extends BaseMapperPlus<GroupMapper, Group> {

    List<Group> selectListByMemberId(@Param("memberId") Long memberId);

}
