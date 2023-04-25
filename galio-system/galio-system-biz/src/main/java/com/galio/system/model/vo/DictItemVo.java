package com.galio.system.model.vo;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典项视图对象
 */
@Data
public class DictItemVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long dictItemId;

    /**
     * 字典项排序
     */
    private Long orderNum;

    /**
     * 字典项标签
     */
    private String label;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 字典表id
     */
    private String dictId;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    private String isDefault;

    /**
     * 状态（1正常 0停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 应用id
     */
    private Long appId;


}
