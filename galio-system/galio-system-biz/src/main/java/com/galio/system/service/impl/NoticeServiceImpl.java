package com.galio.system.service.impl;

import com.galio.core.utils.StringUtil;
import com.galio.core.utils.ObjectUtil;
import com.galio.mybatis.page.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.dto.NoticeDto;
import com.galio.system.model.Notice;
import com.galio.system.repository.NoticeRepository;
import com.galio.system.service.NoticeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 通知公告Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 查询通知公告
     */
    @Override
    public Notice queryById(Long noticeId) {
        return noticeRepository.selectById(noticeId);
    }

        /**
         * 查询通知公告列表
         */
        @Override
        public Page<Notice> queryPageList(PageDto pageDto) {
            return noticeRepository.selectPage(pageDto.build());
        }

    /**
     * 查询通知公告列表
     */
    @Override
    public List<Notice> queryList(NoticeDto dto) {
        Notice entity = ObjectUtil.copyObject(dto, Notice.class);
        Map<String, Object> params = dto.getParams();
        return noticeRepository.selectList(entity,params);
    }

    /**
     * 新增通知公告
     */
    @Override
    public Boolean insertByDto(NoticeDto dto) {
        Notice add = ObjectUtil.copyObject(dto, Notice.class);
        validEntityBeforeSave(add);
        boolean flag = noticeRepository.insert(add) > 0;
        if (flag) {
            dto.setNoticeId(add.getNoticeId());
        }
        return flag;
    }

    /**
     * 修改通知公告
     */
    @Override
    public Boolean updateByDto(NoticeDto dto) {
        Notice update = ObjectUtil.copyObject(dto, Notice.class);
        validEntityBeforeSave(update);
        return noticeRepository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Notice entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除通知公告
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return noticeRepository.deleteBatchIds(ids) > 0;
    }
}
