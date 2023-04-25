package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 字典项对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_item")
public class DictItem extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "dict_item_id")
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
