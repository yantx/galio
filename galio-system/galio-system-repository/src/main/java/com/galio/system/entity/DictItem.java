package com.galio.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.galio.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: galio
 * @Date: 2023-05-30
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
    @TableId(value = "dict_item_id",type = IdType.ASSIGN_ID)
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
    private Long dictId;
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
