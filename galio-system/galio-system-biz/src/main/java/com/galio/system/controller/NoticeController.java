package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.validate.SelectGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Notice;
import com.galio.system.model.vo.NoticeVo;
import com.galio.system.model.dto.NoticeDto;
import com.galio.system.service.NoticeService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 通知公告接口
 * 前端访问路由地址为:/system/notice
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 查询通知公告列表
     */
    @SaCheckPermission("system:notice:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Notice> pageData = noticeService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取通知公告详细信息
     *
     * @param noticeId 主键
     */
    @SaCheckPermission("system:notice:query")
    @GetMapping("/{noticeId}")
    public NoticeVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long noticeId) {
        Notice notice = noticeService.queryById(noticeId);
        return ObjectUtil.copyObject(notice, NoticeVo.class);
    }

    /**
     * 新增通知公告
     */
    @SaCheckPermission("system:notice:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody NoticeDto dto) {
        return noticeService.insertByDto(dto);
    }

    /**
     * 修改通知公告
     */
    @SaCheckPermission("system:notice:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody NoticeDto dto) {
        return noticeService.updateByDto(dto);
    }

    /**
     * 删除通知公告
     *
     * @param noticeIds 主键串
     */
    @SaCheckPermission("system:notice:remove")
    @DeleteMapping("/{noticeIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] noticeIds) {
        return noticeService.deleteWithValidByIds(Arrays.asList(noticeIds), true);
    }
}
