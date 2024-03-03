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
public class ConfigDTO extends BaseEntity {

    /**
     * 参数表主键
     */
    @Schema(description = "参数表主键")
    private Long configId;

    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值")
    private String configValue;

    /**
     * 系统内置（1是 0否）
     */
    @Schema(description = "系统内置（1是 0否）")
    private String configType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 系统配置id
     */
    @Schema(description = "系统配置id")
    private Long appId;


}
