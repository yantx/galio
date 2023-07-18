package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 应用信息业务对象
 */

@Data
@Schema(description = "应用信息业务对象")
public class AppPageReqDto extends PageRequestDto {

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    private String appName;

    /**
     * 应用代码
     */
    @Schema(description = "应用代码")
    private String appCode;

    /**
     * 是否固定（1是，0否）
     */
    @Schema(description = "是否固定（1是，0否）")
    private String isFixed;

    /**
     * 应用状态（1正常，0异常）
     */
    @Schema(description = "应用状态（1正常，0异常）")
    private String status;


}
