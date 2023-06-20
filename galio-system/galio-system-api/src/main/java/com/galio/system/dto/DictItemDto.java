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
 * @Description: 字典项业务对象
 */

@Data
@Schema(description = "字典项业务对象")
public class DictItemDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long dictItemId;

    /**
     * 字典项排序
     */
    @NotNull(message = "字典项排序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "字典项排序")
    private Long orderNum;

    /**
     * 字典项标签
     */
    @NotBlank(message = "字典项标签不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "字典项标签")
    private String label;

    /**
     * 字典项值
     */
    @NotBlank(message = "字典项值不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "字典项值")
    private String value;

    /**
     * 字典表id
     */
    @NotBlank(message = "字典表id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "字典表id")
    private Long dictId;

    /**
     * 字典编号
     */
    @Schema(description = "字典编号")
    private String dictCode;

    /**
     * 样式属性（其他样式扩展）
     */
    @NotBlank(message = "样式属性（其他样式扩展）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @NotBlank(message = "表格回显样式不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "表格回显样式")
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    @NotBlank(message = "是否默认（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "是否默认（1是 0否）")
    private String isDefault;

    /**
     * 状态（1正常 0停用）
     */
    @NotBlank(message = "状态（1正常 0停用）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "状态（1正常 0停用）")
    private String status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;


}
