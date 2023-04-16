package com.galio.system.service.impl;

import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.StringUtil;
    import com.galio.mybatis.page.PageDto;
    import com.galio.mybatis.page.PageVo;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.galio.system.model.dto.NoticeDto;
import com.galio.system.model.vo.NoticeVo;
import com.galio.system.model.Notice;
import com.galio.system.mapper.NoticeMapper;
import com.galio.system.service.NoticeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 通知公告Service业务层处理
 */
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    /**
     * 查询通知公告
     */
    @Override
    public NoticeVo queryById(Long noticeId) {
        return noticeMapper.selectVoById(noticeId);
    }

        /**
         * 查询通知公告列表
         */
        @Override
        public PageVo<NoticeVo> queryPageList(PageDto pageDto) {
            LambdaQueryWrapper<Notice> lqw = Wrappers.lambdaQuery();
            IPage<NoticeVo> pageData = noticeMapper.selectVoPage(pageDto.build(), lqw);
            return PageVo.build(pageData);
        }

    /**
     * 查询通知公告列表
     */
    @Override
    public List<NoticeVo> queryList(NoticeDto dto) {
        LambdaQueryWrapper<Notice> lqw = buildQueryWrapper(dto);
        return noticeMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Notice> buildQueryWrapper(NoticeDto dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<Notice> lqw = Wrappers.lambdaQuery();
                    lqw.eq(StringUtil.isNotBlank(dto.getNoticeTitle()), Notice::getNoticeTitle, dto.getNoticeTitle());
                    lqw.eq(StringUtil.isNotBlank(dto.getNoticeType()), Notice::getNoticeType, dto.getNoticeType());
                    lqw.eq(StringUtil.isNotBlank(dto.getContent()), Notice::getContent, dto.getContent());
                    lqw.eq(StringUtil.isNotBlank(dto.getStatus()), Notice::getStatus, dto.getStatus());
                    lqw.eq(dto.getAppId() != null, Notice::getAppId, dto.getAppId());
        return lqw;
    }

    /**
     * 新增通知公告
     */
    @Override
    public Boolean insertByDto(NoticeDto dto) {
        Notice add = ObjectUtil.copyObject(dto, Notice. class);
        validEntityBeforeSave(add);
        boolean flag = noticeMapper.insert(add) > 0;
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
        Notice update = ObjectUtil.copyObject(dto, Notice. class);
        validEntityBeforeSave(update);
        return noticeMapper.updateById(update) > 0;
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
        return noticeMapper.deleteBatchIds(ids) > 0;
    }
}
