package com.galio.system.dto;

import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 系统访问记录业务对象
 */

@Data
@Schema(description = "系统访问记录业务对象")
public class LogininforDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long infoId;

    /**
     * 登录名
     */
    @NotBlank(message = "登录名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "登录名")
    private String username;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "成员ID")
    private Long memberId;

    /**
     * 登录ip地址
     */
    @NotBlank(message = "登录ip地址不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "登录ip地址")
    private String ipaddr;

    /**
     * 登录状态（0成功 1失败）
     */
    @NotBlank(message = "登录状态（0成功 1失败）不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "登录状态（0成功 1失败）")
    private String status;

    /**
     * 提示信息
     */
    @NotBlank(message = "提示信息不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "提示信息")
    private String msg;

    /**
     * 访问时间
     */
    @NotNull(message = "访问时间不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "访问时间")
    private LocalDateTime accessTime;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;


}
