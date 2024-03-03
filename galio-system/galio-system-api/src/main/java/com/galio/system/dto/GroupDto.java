package com.galio.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 群组信息业务对象
 */

@Data
@Schema(description = "群组信息业务对象")
public class GroupDTO extends BaseEntity {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long groupId;

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

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Long orderNum;

    /**
     * 状态1正常 0异常
     */
    @Schema(description = "状态1正常 0异常")
    private String status;

    /**
     * 应用id
     */
    @Schema(description = "应用id")
    private Long appId;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 关联关联角色ID集合
     */
    @Schema(description = "角色ID集合")
    private List<Long> roleIds;

}
