package com.galio.system.service;

import com.galio.system.model.vo.NoticeVo;
import com.galio.system.model.dto.NoticeDto;
import com.galio.mybatis.page.PageVo;
import com.galio.mybatis.page.PageDto;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 通知公告Service接口
 */
public interface NoticeService {

    /**
     * 查询通知公告
     */
    NoticeVo queryById(Long noticeId);

    /**
     * 查询通知公告列表
     */
    PageVo<NoticeVo> queryPageList(PageDto pageDto);

    /**
     * 查询通知公告列表
     */
    List<NoticeVo> queryList(NoticeDto dto);

    /**
     * 修改通知公告
     */
    Boolean insertByDto(NoticeDto dto);

    /**
     * 修改通知公告
     */
    Boolean updateByDto(NoticeDto dto);

    /**
     * 校验并批量删除通知公告信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
