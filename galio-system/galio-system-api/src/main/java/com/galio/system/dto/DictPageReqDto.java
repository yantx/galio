package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 字典业务对象
 */

@Data
@Schema(description = "字典业务对象")
public class DictPageReqDTO extends PageRequestDTO {

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典编号
     */
    @Schema(description = "字典编号")
    private String dictCode;

}
