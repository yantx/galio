package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典项业务对象
 */

@Data
public class DictItemDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    private Long dictItemId;

    /**
     * 字典项排序
     */
    @NotNull(message = "字典项排序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long orderNum;

    /**
     * 字典项标签
     */
    @NotBlank(message = "字典项标签不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String label;

    /**
     * 字典项值
     */
    @NotBlank(message = "字典项值不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String value;

    /**
     * 字典表id
     */
    @NotBlank(message = "字典表id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String dictId;

    /**
     * 样式属性（其他样式扩展）
     */
    @NotBlank(message = "样式属性（其他样式扩展）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String cssClass;

    /**
     * 表格回显样式
     */
    @NotBlank(message = "表格回显样式不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    @NotBlank(message = "是否默认（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String isDefault;

    /**
     * 状态（1正常 0停用）
     */
    @NotBlank(message = "状态（1正常 0停用）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;


}
