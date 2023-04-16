package com.galio.system.service;

import com.galio.system.model.vo.ConfigVo;
import com.galio.system.model.dto.ConfigDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 应用信息Service接口
 */
public interface ConfigService {

    /**
     * 查询应用信息
     */
    ConfigVo queryById(Long configId);

    /**
     * 查询应用信息列表
     */
    PageVo<ConfigVo> queryPageList(PageDto pageDto);

    /**
     * 查询应用信息列表
     */
    List<ConfigVo> queryList(ConfigDto dto);

    /**
     * 修改应用信息
     */
    Boolean insertByDto(ConfigDto dto);

    /**
     * 修改应用信息
     */
    Boolean updateByDto(ConfigDto dto);

    /**
     * 校验并批量删除应用信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
