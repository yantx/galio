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
    @Schema(description = "主键")
    private Long infoId;

    /**
     * 登录名
     */
    @Schema(description = "登录名")
    private String username;

    /**
     * 成员ID
     */
    @Schema(description = "成员ID")
    private Long memberId;

    /**
     * 登录ip地址
     */
    @Schema(description = "登录ip地址")
    private String ipaddr;

    /**
     * 登录状态（0成功 1失败）
     */
    @Schema(description = "登录状态（0成功 1失败）")
    private String status;

    /**
     * 提示信息
     */
    @Schema(description = "提示信息")
    private String msg;

    /**
     * 访问时间
     */
    @Schema(description = "访问时间")
    private LocalDateTime accessTime;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;


}
