package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 字典视图对象
 */
@Data
@Schema(description = "字典视图对象")
public class DictVo {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    @Schema(description = "字典主键")
    private Long dictId;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典编号
     */
    @Schema(description = "字典编号")
    private String dictCode;

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
