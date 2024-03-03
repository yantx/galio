package com.galio.system.service;

import com.galio.core.model.PageRequestDTO;
import com.galio.system.dto.NoticeDTO;
import com.galio.system.entity.Notice;
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
    Notice getById(Long noticeId);

    /**
     * 查询通知公告列表
     */
    Page<Notice> listPage(PageRequestDTO pageRequestDTO);

    /**
     * 查询通知公告列表
     */
    List<Notice> list(NoticeDTO dto);

    /**
     * 修改通知公告
     */
    Boolean save(NoticeDTO dto);

    /**
     * 修改通知公告
     */
    Boolean update(NoticeDTO dto);

    /**
     * 校验并批量删除通知公告信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
