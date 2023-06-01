package com.galio.system.service;

import com.galio.system.dto.DictDto;
import com.galio.system.model.Dict;
import com.galio.mybatis.page.PageDto;
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
    Dict queryById(Long dictId);

    /**
     * 查询字典列表
     */
    Page<Dict> queryPageList(PageDto pageDto);

    /**
     * 查询字典列表
     */
    List<Dict> queryList(DictDto dto);

    /**
     * 修改字典
     */
    Boolean insertByDto(DictDto dto);

    /**
     * 修改字典
     */
    Boolean updateByDto(DictDto dto);

    /**
     * 校验并批量删除字典信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
