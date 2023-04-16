package com.galio.system.model.vo;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 功能视图对象
 */
@Data
public class FunctionVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long functionId;

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 父功能id
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Long orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String queryParam;

    /**
     * 是否为外链（1是 0否）
     */
    private String isFrame;

    /**
     * 是否缓存（1缓存 0不缓存）
     */
    private String isCache;

    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    private String functionType;

    /**
     * 适用范围（1pc端 2大屏端 3移动端）
     */
    private String belong;

    /**
     * 功能状态（1显示 0隐藏）
     */
    private String visible;

    /**
     * 是否删除（1是 0否）
     */
    private String deleteFlag;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 功能图标
     */
    private String icon;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 备注
     */
    private String remark;


}