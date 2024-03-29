package com.galio.system.dto;

import com.galio.core.model.PageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 成员信息业务对象
 */

@Data
@Schema(description = "成员信息业务对象")
public class MemberPageReqDTO extends PageRequestDTO {

    /**
     * 雇员id
     */
    @Schema(description = "雇员id")
    private Long employeeId;

    /**
     * 登陆名
     */
    @Schema(description = "登陆名")
    private String username;

}
