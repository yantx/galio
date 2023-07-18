package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 应用信息分页查询对象
 */

@Data
@Schema(description = "应用信息分页查询对象")
public class ConfigPageReqDto extends PageRequestDto {

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
     * 系统内置（1是 0否）
     */
    @Schema(description = "系统内置（1是 0否）")
    private String configType;

}
