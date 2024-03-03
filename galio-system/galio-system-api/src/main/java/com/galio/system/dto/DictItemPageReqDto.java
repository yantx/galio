package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 字典项业务对象
 */

@Data
@Schema(description = "字典项业务对象")
public class DictItemPageReqDTO extends PageRequestDTO {

    /**
     * 字典项标签
     */
    @Schema(description = "字典项标签")
    private String label;

    /**
     * 字典表id
     */
    @Schema(description = "字典表id")
    private String dictId;

}
