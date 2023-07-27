package com.galio.system.repository;

import com.galio.core.utils.StringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.galio.system.model.Notice;
import com.galio.system.mapper.NoticeMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 通知公告Repository持久层处理
 */
@Repository
@RequiredArgsConstructor
public class NoticeRepository{

    private final NoticeMapper noticeMapper;

    /**
     * 查询通知公告
     */
    public Notice selectById(Long noticeId) {
        return noticeMapper.selectById(noticeId);
    }

        /**
         * 查询通知公告列表
         */
        public Page<Notice> selectPage(Page page) {
            LambdaQueryWrapper<Notice> lqw = Wrappers.lambdaQuery();
            return noticeMapper.selectPage(page, lqw);
        }

    /**
     * 查询通知公告列表
     */
    public List<Notice> selectList(Notice notice) {
        LambdaQueryWrapper<Notice> lqw = buildQueryWrapper(notice);
        return noticeMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<Notice> buildQueryWrapper(Notice entity) {
        LambdaQueryWrapper<Notice> lqw = Wrappers.lambdaQuery();
                    lqw.eq(StringUtil.isNotBlank(entity.getNoticeTitle()), Notice::getNoticeTitle, entity.getNoticeTitle());
                    lqw.eq(StringUtil.isNotBlank(entity.getNoticeType()), Notice::getNoticeType, entity.getNoticeType());
                    lqw.eq(StringUtil.isNotBlank(entity.getContent()), Notice::getContent, entity.getContent());
                    lqw.eq(StringUtil.isNotBlank(entity.getStatus()), Notice::getStatus, entity.getStatus());
                    lqw.eq(entity.getAppId() != null, Notice::getAppId, entity.getAppId());
        return lqw;
    }

    /**
     * 新增通知公告
     */
    public int insert(Notice entity) {
        validEntityBeforeSave(entity);
        int flag = noticeMapper.insert(entity);
        return flag;
    }

    /**
     * 修改通知公告
     */
    public int updateById(Notice entity) {
        validEntityBeforeSave(entity);
        return noticeMapper.updateById(entity);
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
    public int deleteBatchIds(Collection<Long> ids) {
        return noticeMapper.deleteBatchIds(ids);
    }
}
