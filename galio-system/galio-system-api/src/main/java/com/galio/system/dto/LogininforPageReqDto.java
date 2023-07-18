package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 系统访问日志分页查询对象
 */

@Data
@Schema(description = "系统访问日志分页查询对象")
public class LogininforPageReqDto extends PageRequestDto {

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

}
