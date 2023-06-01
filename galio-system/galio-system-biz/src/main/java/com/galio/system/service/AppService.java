package com.galio.system.service;

import com.galio.system.dto.AppDto;
import com.galio.system.model.App;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息Service接口
 */
public interface AppService {

    /**
     * 查询应用信息
     */
    App queryById(Long appId);

    /**
     * 查询应用信息列表
     */
    Page<App> queryPageList(PageDto pageDto);

    /**
     * 查询应用信息列表
     */
    List<App> queryList(AppDto dto);

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
