package com.galio.system.service;

import com.galio.system.model.vo.DictVo;
import com.galio.system.model.dto.DictDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典Service接口
 */
public interface DictService {

    /**
     * 查询字典
     */
    DictVo queryById(Long dictId);

    /**
     * 查询字典列表
     */
    PageVo<DictVo> queryPageList(PageDto pageDto);

    /**
     * 查询字典列表
     */
    List<DictVo> queryList(DictDto dto);

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
