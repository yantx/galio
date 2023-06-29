package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 系统配置信息视图对象
 */
@Data
@Schema(description = "系统配置信息视图对象")
public class ConfigVo {

    private static final long serialVersionUID = 1L;

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
