package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 雇员分页查询对象
 */

@Data
@Schema(description = "雇员分页查询对象")
public class EmployeePageReqDto extends PageRequestDto {

    /**
     * 机构id
     */
    @Schema(description = "机构id")
    private Long orgId;

    /**
     * 雇员名
     */
    @Schema(description = "雇员名")
    private String employeeName;

    /**
     * 工号
     */
    @Schema(description = "工号")
    private String employeeNo;

}
