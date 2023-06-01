package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 字典项视图对象
 */
@Data
@Schema(description = "字典项视图对象")
public class DictItemVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long dictItemId;

    /**
     * 字典项排序
     */
    @Schema(description = "字典项排序")
    private Long orderNum;

    /**
     * 字典项标签
     */
    @Schema(description = "字典项标签")
    private String label;

    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    private String value;

    /**
     * 字典表id
     */
    @Schema(description = "字典表id")
    private String dictId;

    /**
     * 样式属性（其他样式扩展）
     */
    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @Schema(description = "表格回显样式")
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    @Schema(description = "是否默认（1是 0否）")
    private String isDefault;

    /**
     * 状态（1正常 0停用）
     */
    @Schema(description = "状态（1正常 0停用）")
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;


}
