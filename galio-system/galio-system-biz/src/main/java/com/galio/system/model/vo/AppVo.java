package com.galio.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 应用信息视图对象
 */
@Data
@Schema(description = "应用信息视图对象")
public class AppVo {

    private static final long serialVersionUID = 1L;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;

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
     * 应用图标
     */
    @Schema(description = "应用图标")
    private String appIcon;

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

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


}
