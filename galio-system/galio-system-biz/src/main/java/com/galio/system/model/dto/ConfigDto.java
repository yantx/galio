package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 应用信息业务对象
 */

@Data
public class ConfigDto extends BaseEntity {

    /**
     * 参数表主键
     */
    @NotNull(message = "参数表主键不能为空", groups = { UpdateGroup.class })
    private Long configId;

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String configName;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数键名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String configKey;

    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String configValue;

    /**
     * 系统内置（1是 0否）
     */
    @NotBlank(message = "系统内置（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String configType;

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
