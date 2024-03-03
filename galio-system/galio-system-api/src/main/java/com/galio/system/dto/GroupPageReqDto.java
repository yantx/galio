package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 群组信息分页查询对象
 */

@Data
@Schema(description = "群组信息分页查询对象")
public class GroupPageReqDTO extends PageRequestDTO {

    /**
     * 组代码
     */
    @Schema(description = "组代码")
    private Long groupCode;

    /**
     * 组名称
     */
    @Schema(description = "组名称")
    private String groupName;

}
