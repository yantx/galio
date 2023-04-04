package com.galio.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.galio.core.constant.MemberConstants;
import com.galio.mybatis.mapper.BaseMapperPlus;
import com.galio.system.entity.SysDict;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-02-14
 * @Description: 字典表 持久层
 */
public interface SysDictMapper extends BaseMapperPlus<SysDictMapper, SysDict, SysDict> {

    default List<SysDict> selectDictDataId(String dictId) {
        return selectList(
            new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getStatus, MemberConstants.DICT_NORMAL)
                .eq(SysDict::getDictId, dictId)
                .orderByAsc(SysDict::getOrderNum));
    }

}
