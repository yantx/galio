package com.galio.system.service;

import com.galio.system.model.vo.AppVo;
import com.galio.system.model.dto.AppDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 应用信息Service接口
 */
public interface AppService {

    /**
     * 查询应用信息
     */
    AppVo queryById(Long appId);

    /**
     * 查询应用信息列表
     */
    PageVo<AppVo> queryPageList(PageDto pageDto);

    /**
     * 查询应用信息列表
     */
    List<AppVo> queryList(AppDto dto);

    /**
     * 修改应用信息
     */
    Boolean insertByDto(AppDto dto);

    /**
     * 修改应用信息
     */
    Boolean updateByDto(AppDto dto);

    /**
     * 校验并批量删除应用信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
