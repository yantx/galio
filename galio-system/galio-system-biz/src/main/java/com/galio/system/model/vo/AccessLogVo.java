package com.galio.system.model.vo;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 系统访问记录视图对象
 */
@Data
@Schema(description = "系统访问记录视图对象")
public class AccessLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long accessId;

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
