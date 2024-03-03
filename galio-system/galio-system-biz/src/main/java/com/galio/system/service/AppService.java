package com.galio.system.service;

import com.galio.system.dto.AppDTO;
import com.galio.system.dto.AppPageReqDTO;
import com.galio.system.entity.App;
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
    App getById(Long appId);

    /**
     * 查询应用信息列表
     */
    Page<App> listPage(AppPageReqDTO pageRequestDTO);

    /**
     * 查询应用信息列表
     */
    List<App> list(AppDTO dto);

    /**
     * 修改应用信息
     */
    Boolean save(AppDTO dto);

    /**
     * 修改应用信息
     */
    Boolean update(AppDTO dto);

    /**
     * 校验并批量删除应用信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
