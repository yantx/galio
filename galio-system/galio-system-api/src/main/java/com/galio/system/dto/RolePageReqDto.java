package com.galio.system.dto;

import com.galio.core.model.PageRequestDto;
import com.galio.core.validate.SelectGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-07-17
 * @Description: 角色分页查询对象
 */

@Data
@Schema(description = "角色分页查询对象")
public class RolePageReqDto extends PageRequestDto {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

}
