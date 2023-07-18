package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.DictItemDto;
import com.galio.system.model.DictItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典项Service接口
 */
public interface DictItemService {

    /**
     * 查询字典项
     */
    DictItem queryById(Long dictItemId);

    /**
     * 查询字典项列表
     */
    Page<DictItem> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询字典项列表
     */
    List<DictItem> queryList(DictItemDto dto);

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
