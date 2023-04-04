package com.galio.system.mapper;

import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.model.SysGroup;

import java.util.List;


/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 群组表 持久层
 */
public interface SysGroupMapper extends BaseMapperPlus<SysGroupMapper, SysGroup, SysGroup> {

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    List<Long> selectPostListByUserId(Long userId);

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    List<SysGroup> selectPostsByUserName(String userName);

}
