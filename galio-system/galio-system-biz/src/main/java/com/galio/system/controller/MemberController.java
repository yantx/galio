package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.model.PageRequestDTO;
import com.galio.mybatis.page.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.galio.satoken.tools.helper.MemberContextHelper;
import com.galio.system.dto.LoginMemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.vo.MemberVo;
import com.galio.system.dto.MemberDTO;
import com.galio.system.service.MemberService;

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
    public PageVO<?> page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<MemberVo> pageData = memberService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
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
        return memberService.getById(memberId);
    }

    /**
     * 查询登录用户基本信息、角色、权限、机构信息
     * @return LoginMemberDTO
     */
    @Operation(summary = "查询用户自己的关联信息")
    @GetMapping("/personal/info")
    public LoginMemberDTO getCurrentMemberContext() {
        return MemberContextHelper.getLoginMember();
    }
    /**
     * 新增成员信息
     */
    @Operation(summary = "新增成员信息")
    @SaCheckPermission("system:member:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody MemberDTO dto) {
        return memberService.save(dto);
    }

    /**
     * 修改成员信息
     */
    @Operation(summary = "修改成员信息")
    @SaCheckPermission("system:member:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody MemberDTO dto) {
        return memberService.update(dto);
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
        return memberService.deleteWithValidByIds(Arrays.asList(memberIds));
    }
}
