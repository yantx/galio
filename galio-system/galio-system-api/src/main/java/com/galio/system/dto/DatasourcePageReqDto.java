package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 数据源信息业务对象
 */

@Data
@Schema(description = "数据源信息业务对象")
public class DatasourcePageReqDto extends PageRequestDto {

    /**
     * 数据源名称
     */
    @Schema(description = "数据源名称")
    private String datasourceName;

}
