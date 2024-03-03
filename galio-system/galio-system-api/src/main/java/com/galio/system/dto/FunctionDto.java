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
public class FunctionDTO extends BaseEntity {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long functionId;

    /**
     * 功能名称
     */
    @Schema(description = "功能名称")
    private String functionName;

    /**
     * 父功能id
     */
    @Schema(description = "父功能id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;

    /**
     * 路由参数
     */
    @Schema(description = "路由参数")
    private String queryParam;

    /**
     * 是否为外链（1是 0否）
     */
    @Schema(description = "是否为外链（1是 0否）")
    private String isFrame;

    /**
     * 是否缓存（1缓存 0不缓存）
     */
    @Schema(description = "是否缓存（1缓存 0不缓存）")
    private String isCache;

    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @Schema(description = "菜单类型（1目录 2菜单 3按钮）")
    private String functionType;

    /**
     * 适用范围（1pc端 2大屏端 3移动端）
     */
    @Schema(description = "适用范围（1pc端 2大屏端 3移动端）")
    private String belong;

    /**
     * 功能状态（1显示 0隐藏）
     */
    @Schema(description = "功能状态（1显示 0隐藏）")
    private String visible;

    /**
     * 是否删除（1是 0否）
     */
    @Schema(description = "是否删除（1是 0否）")
    private String deleteFlag;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String perms;

    /**
     * 功能图标
     */
    @Schema(description = "功能图标")
    private String icon;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


}
