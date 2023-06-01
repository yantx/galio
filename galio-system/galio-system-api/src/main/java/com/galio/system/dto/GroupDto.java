package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 群组信息业务对象
 */

@Data
@Schema(description = "群组信息业务对象")
public class GroupDto extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { UpdateGroup.class })
    @Schema(description = "主键")
    private Long groupId;

    /**
     * 组代码
     */
    @NotNull(message = "组代码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "组代码")
    private Long groupCode;

    /**
     * 组名称
     */
    @NotBlank(message = "组名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "组名称")
    private String groupName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 状态1正常 0异常
     */
    @NotBlank(message = "状态1正常 0异常不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "备注")
    private String remark;


}
