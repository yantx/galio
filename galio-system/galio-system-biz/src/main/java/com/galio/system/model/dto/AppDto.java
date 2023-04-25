package com.galio.system.model.dto;

import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 应用信息业务对象
 */

@Data
public class AppDto extends BaseEntity {

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { UpdateGroup.class })
    private Long appId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String appName;

    /**
     * 应用代码
     */
    @NotBlank(message = "应用代码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String appCode;

    /**
     * 应用图标
     */
    @NotBlank(message = "应用图标不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String appIcon;

    /**
     * 是否固定（1是，0否）
     */
    @NotBlank(message = "是否固定（1是，0否）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String isFixed;

    /**
     * 应用状态（1正常，0异常）
     */
    @NotBlank(message = "应用状态（1正常，0异常）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
