package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.DictItemDTO;
import com.galio.system.entity.DictItem;
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
    DictItem getById(Long dictItemId);

    /**
     * 查询字典项列表
     */
    Page<DictItem> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询字典项列表
     */
    List<DictItem> list(DictItemDTO dto);

    /**
     * 修改字典项
     */
    Boolean save(DictItemDTO dto);

    /**
     * 修改字典项
     */
    Boolean update(DictItemDTO dto);

    /**
     * 校验并批量删除字典项信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
