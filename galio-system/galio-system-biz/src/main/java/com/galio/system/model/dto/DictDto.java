package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 字典业务对象
 */

@Data
public class DictDto extends BaseEntity {

    /**
     * 字典主键
     */
    @NotNull(message = "字典主键不能为空", groups = { UpdateGroup.class })
    private Long dictId;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String dictName;

    /**
     * 字典编号
     */
    @NotBlank(message = "字典编号不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String dictCode;

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
