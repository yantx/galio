package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 数据集信息业务对象
 */

@Data
@Schema(description = "数据集信息业务对象")
public class DatasetPageReqDto extends PageRequestDto {

    /**
     * 数据集代码
     */
    @Schema(description = "数据集代码")
    private String datasetCode;

    /**
     * 数据集名称
     */
    @Schema(description = "数据集名称")
    private String datasetName;

}
