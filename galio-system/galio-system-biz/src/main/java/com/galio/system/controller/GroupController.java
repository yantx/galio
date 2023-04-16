package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.validate.SelectGroup;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.model.vo.GroupVo;
import com.galio.system.model.dto.GroupDto;
import com.galio.system.service.GroupService;

import java.util.List;
import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 群组信息接口
 * 前端访问路由地址为:/system/group
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    /**
     * 查询群组信息列表
     */
    @SaCheckPermission("system:group:list")
    @GetMapping("/list")
    public PageVo list(@RequestBody PageDto pageDto) {
        return groupService.queryPageList(pageDto);
    }

    /**
     * 获取群组信息详细信息
     *
     * @param groupId 主键
     */
    @SaCheckPermission("system:group:query")
    @GetMapping("/{groupId}")
    public GroupVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long groupId) {
        return groupService.queryById(groupId);
    }

    /**
     * 新增群组信息
     */
    @SaCheckPermission("system:group:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody GroupDto dto) {
        return groupService.insertByDto(dto);
    }

    /**
     * 修改群组信息
     */
    @SaCheckPermission("system:group:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody GroupDto dto) {
        return groupService.updateByDto(dto);
    }

    /**
     * 删除群组信息
     *
     * @param groupIds 主键串
     */
    @SaCheckPermission("system:group:remove")
    @DeleteMapping("/{groupIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] groupIds) {
        return groupService.deleteWithValidByIds(Arrays.asList(groupIds), true);
    }
}
