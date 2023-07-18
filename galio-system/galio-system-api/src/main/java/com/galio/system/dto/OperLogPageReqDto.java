package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 操作日志分页查询对象
 */

@Data
@Schema(description = "操作日志分页查询对象")
public class OperLogPageReqDto extends PageRequestDto {

    /**
     * 模块标题
     */
    @Schema(description = "模块标题")
    private String title;

}
