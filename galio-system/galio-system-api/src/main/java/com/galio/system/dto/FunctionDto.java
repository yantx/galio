package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 功能业务对象
 */

@Data
@Schema(description = "功能业务对象")
public class FunctionDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long functionId;

    /**
     * 功能名称
     */
    @NotBlank(message = "功能名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "功能名称")
    private String functionName;

    /**
     * 父功能id
     */
    @NotNull(message = "父功能id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "父功能id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 路由地址
     */
    @NotBlank(message = "路由地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @NotBlank(message = "组件路径不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "组件路径")
    private String component;

    /**
     * 路由参数
     */
    @NotBlank(message = "路由参数不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "路由参数")
    private String queryParam;

    /**
     * 是否为外链（1是 0否）
     */
    @NotBlank(message = "是否为外链（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "是否为外链（1是 0否）")
    private String isFrame;

    /**
     * 是否缓存（1缓存 0不缓存）
     */
    @NotBlank(message = "是否缓存（1缓存 0不缓存）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "是否缓存（1缓存 0不缓存）")
    private String isCache;

    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @NotBlank(message = "菜单类型（1目录 2菜单 3按钮）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "菜单类型（1目录 2菜单 3按钮）")
    private String functionType;

    /**
     * 适用范围（1pc端 2大屏端 3移动端）
     */
    @NotBlank(message = "适用范围（1pc端 2大屏端 3移动端）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "适用范围（1pc端 2大屏端 3移动端）")
    private String belong;

    /**
     * 功能状态（1显示 0隐藏）
     */
    @NotBlank(message = "功能状态（1显示 0隐藏）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "功能状态（1显示 0隐藏）")
    private String visible;

    /**
     * 是否删除（1是 0否）
     */
    @NotBlank(message = "是否删除（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "是否删除（1是 0否）")
    private String deleteFlag;

    /**
     * 权限标识
     */
    @NotBlank(message = "权限标识不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "权限标识")
    private String perms;

    /**
     * 功能图标
     */
    @NotBlank(message = "功能图标不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "功能图标")
    private String icon;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;


}
