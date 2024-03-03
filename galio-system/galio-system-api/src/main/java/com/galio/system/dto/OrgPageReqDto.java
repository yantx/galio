package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 机构分页查询对象
 */

@Data
@Schema(description = "机构分页查询对象")
public class OrgPageReqDTO extends PageRequestDTO {

    /**
     * 机构名
     */
    @Schema(description = "机构名")
    private String orgName;

    /**
     * 机构类别（1部门 2公司）
     */
    @Schema(description = "机构类别（1部门 2公司）")
    private String category;

    /**
     * 状态1正常 0异常
     */
    @Schema(description = "状态1正常 0异常")
    private String status;

}
