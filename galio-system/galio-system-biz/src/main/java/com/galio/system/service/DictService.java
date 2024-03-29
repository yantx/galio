package com.galio.system.service;

import com.galio.system.dto.DictDTO;
import com.galio.system.entity.Dict;
import com.galio.core.model.PageRequestDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典Service接口
 */
public interface DictService {

    /**
     * 查询字典
     */
    Dict getById(Long dictId);

    /**
     * 查询字典列表
     */
    Page<Dict> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询字典列表
     */
    List<Dict> list(DictDTO dto);

    /**
     * 修改字典
     */
    Boolean save(DictDTO dto);

    /**
     * 修改字典
     */
    Boolean update(DictDTO dto);

    /**
     * 校验并批量删除字典信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
    public void loadingDictCache();
}
