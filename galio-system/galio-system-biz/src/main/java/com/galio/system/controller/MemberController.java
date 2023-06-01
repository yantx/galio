package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.validate.SelectGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.Member;
import com.galio.system.model.vo.MemberVo;
import com.galio.system.dto.MemberDto;
import com.galio.system.service.MemberService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 成员信息接口
 * 前端访问路由地址为:/system/member
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "成员信息API")
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 查询成员信息列表
     */
    @Operation(summary = "查询成员信息列表")
    @SaCheckPermission("system:member:page")
    @PostMapping("/page")
    public PageVo page(@RequestBody PageDto pageDto) {
        IPage<Member> pageData = memberService.queryPageList(pageDto);
        return PageVo.build(pageData);
    }

    /**
     * 获取成员信息详细信息
     *
     * @param memberId 主键
     */
    @Operation(summary = "查询成员信息详情")
    @SaCheckPermission("system:member:query")
    @GetMapping("/{memberId}")
    public MemberVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long memberId) {
        Member member = memberService.queryById(memberId);
        return ObjectUtil.copyObject(member, MemberVo.class);
    }

    /**
     * 新增成员信息
     */
    @Operation(summary = "新增成员信息")
    @SaCheckPermission("system:member:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody MemberDto dto) {
        return memberService.insertByDto(dto);
    }

    /**
     * 修改成员信息
     */
    @Operation(summary = "修改成员信息")
    @SaCheckPermission("system:member:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody MemberDto dto) {
        return memberService.updateByDto(dto);
    }

    /**
     * 删除成员信息
     *
     * @param memberIds 主键串
     */
    @Operation(summary = "删除成员信息")
    @SaCheckPermission("system:member:remove")
    @DeleteMapping("/{memberIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] memberIds) {
        return memberService.deleteWithValidByIds(Arrays.asList(memberIds), true);
    }
}
