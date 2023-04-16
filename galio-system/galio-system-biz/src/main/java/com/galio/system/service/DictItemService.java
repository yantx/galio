package com.galio.system.service;

import com.galio.system.model.vo.DictItemVo;
import com.galio.system.model.dto.DictItemDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典项Service接口
 */
public interface DictItemService {

    /**
     * 查询字典项
     */
    DictItemVo queryById(Long dictItemId);

    /**
     * 查询字典项列表
     */
    PageVo<DictItemVo> queryPageList(PageDto pageDto);

    /**
     * 查询字典项列表
     */
    List<DictItemVo> queryList(DictItemDto dto);

    /**
     * 修改字典项
     */
    Boolean insertByDto(DictItemDto dto);

    /**
     * 修改字典项
     */
    Boolean updateByDto(DictItemDto dto);

    /**
     * 校验并批量删除字典项信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
