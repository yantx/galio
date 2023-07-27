package com.galio.system.service;

import com.galio.core.model.PageRequestDto;
import com.galio.system.dto.NoticeDto;
import com.galio.system.model.Notice;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 通知公告Service接口
 */
public interface NoticeService {

    /**
     * 查询通知公告
     */
    Notice queryById(Long noticeId);

    /**
     * 查询通知公告列表
     */
    Page<Notice> queryPageList(PageRequestDto pageRequestDto);

    /**
     * 查询通知公告列表
     */
    List<Notice> queryList(NoticeDto dto);

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
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
