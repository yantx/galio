package com.galio.system.model.dto;

import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 功能业务对象
 */

@Data
public class FunctionDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long functionId;

    /**
     * 功能名称
     */
    @NotBlank(message = "功能名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String functionName;

    /**
     * 父功能id
     */
    @NotNull(message = "父功能id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long parentId;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long orderNum;

    /**
     * 路由地址
     */
    @NotBlank(message = "路由地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String path;

    /**
     * 组件路径
     */
    @NotBlank(message = "组件路径不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String component;

    /**
     * 路由参数
     */
    @NotBlank(message = "路由参数不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String queryParam;

    /**
     * 是否为外链（1是 0否）
     */
    @NotBlank(message = "是否为外链（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String isFrame;

    /**
     * 是否缓存（1缓存 0不缓存）
     */
    @NotBlank(message = "是否缓存（1缓存 0不缓存）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String isCache;

    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @NotBlank(message = "菜单类型（1目录 2菜单 3按钮）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String functionType;

    /**
     * 适用范围（1pc端 2大屏端 3移动端）
     */
    @NotBlank(message = "适用范围（1pc端 2大屏端 3移动端）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String belong;

    /**
     * 功能状态（1显示 0隐藏）
     */
    @NotBlank(message = "功能状态（1显示 0隐藏）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String visible;

    /**
     * 是否删除（1是 0否）
     */
    @NotBlank(message = "是否删除（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String deleteFlag;

    /**
     * 权限标识
     */
    @NotBlank(message = "权限标识不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String perms;

    /**
     * 功能图标
     */
    @NotBlank(message = "功能图标不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String icon;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
