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
 * @Description: 应用信息业务对象
 */

@Data
@Schema(description = "应用信息业务对象")
public class AppDto extends BaseEntity {

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用名称")
    private String appName;

    /**
     * 应用代码
     */
    @NotBlank(message = "应用代码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用代码")
    private String appCode;

    /**
     * 应用图标
     */
    @NotBlank(message = "应用图标不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用图标")
    private String appIcon;

    /**
     * 是否固定（1是，0否）
     */
    @NotBlank(message = "是否固定（1是，0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "是否固定（1是，0否）")
    private String isFixed;

    /**
     * 应用状态（1正常，0异常）
     */
    @NotBlank(message = "应用状态（1正常，0异常）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用状态（1正常，0异常）")
    private String status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;


}
