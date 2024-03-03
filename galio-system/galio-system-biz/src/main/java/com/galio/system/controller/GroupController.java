package com.galio.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import com.galio.core.model.PageRequestDTO;
import com.galio.mybatis.page.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.galio.system.entity.Group;
import com.galio.system.model.vo.GroupVo;
import com.galio.system.dto.GroupDTO;
import com.galio.system.service.GroupService;

import java.util.Arrays;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 群组信息接口
 * 前端访问路由地址为:/system/group
 */
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "群组信息API")
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    /**
     * 查询群组信息列表
     */
    @Operation(summary = "查询群组信息列表")
    @SaCheckPermission("system:group:page")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageRequestDTO pageRequestDTO) {
        IPage<Group> pageData = groupService.listPage(pageRequestDTO);
        return PageVO.build(pageData);
    }

    /**
     * 获取群组信息详细信息
     *
     * @param groupId 主键
     */
    @Operation(summary = "查询群组信息详情")
    @SaCheckPermission("system:group:query")
    @GetMapping("/{groupId}")
    public GroupVo getInfo(@NotNull(message = "主键不能为空") @PathVariable Long groupId) {
        Group group = groupService.getById(groupId);
        return ObjectUtil.copyObject(group, GroupVo.class);
    }

    /**
     * 新增群组信息
     */
    @Operation(summary = "新增群组信息")
    @SaCheckPermission("system:group:add")
    @PostMapping()
    public Object add(@Validated(InsertGroup.class) @RequestBody GroupDTO dto) {
        return groupService.save(dto);
    }

    /**
     * 修改群组信息
     */
    @Operation(summary = "修改群组信息")
    @SaCheckPermission("system:group:edit")
    @PutMapping()
    public Object edit(@Validated(UpdateGroup.class) @RequestBody GroupDTO dto) {
        return groupService.update(dto);
    }

    /**
     * 删除群组信息
     *
     * @param groupIds 主键串
     */
    @Operation(summary = "删除群组信息")
    @SaCheckPermission("system:group:remove")
    @DeleteMapping("/{groupIds}")
    public Object remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] groupIds) {
        return groupService.deleteWithValidByIds(Arrays.asList(groupIds));
    }
}
