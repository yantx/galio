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
 * @Description: 系统配置信息业务对象
 */

@Data
@Schema(description = "系统配置信息业务对象")
public class ConfigDto extends BaseEntity {

    /**
     * 参数表主键
     */
    @NotNull(message = "参数表主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "参数表主键")
    private Long configId;

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数键名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "参数键值")
    private String configValue;

    /**
     * 系统内置（1是 0否）
     */
    @NotBlank(message = "系统内置（1是 0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "系统内置（1是 0否）")
    private String configType;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;

    /**
     * 系统配置id
     */
    @NotNull(message = "系统配置id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "系统配置id")
    private Long appId;


}
