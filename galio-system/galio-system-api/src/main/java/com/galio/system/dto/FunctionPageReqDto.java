package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 功能分页查询对象
 */

@Data
@Schema(description = "功能分页查询对象")
public class FunctionPageReqDTO extends PageRequestDTO {

    /**
     * 功能名称
     */
    @Schema(description = "功能名称")
    private String functionName;

}
