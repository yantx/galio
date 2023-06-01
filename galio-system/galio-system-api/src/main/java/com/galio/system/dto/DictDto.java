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
 * @Description: 字典业务对象
 */

@Data
@Schema(description = "字典业务对象")
public class DictDto extends BaseEntity {

    /**
     * 字典主键
     */
    @NotNull(message = "字典主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "字典主键")
    private Long dictId;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典编号
     */
    @NotBlank(message = "字典编号不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "字典编号")
    private String dictCode;

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
