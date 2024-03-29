package com.galio.system.dto;

import com.galio.core.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-01-09
 * @Description: 角色业务对象
 */
@Data
@Schema(description = "角色业务对象")
public class RoleDTO extends BaseEntity {
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色权限
     */
    @Schema(description = "角色Key")
    private String roleKey;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    @Schema(description = "数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）")
    private String dataScope;

    @Schema(description = "显示顺序")
    private Integer orderNum;

    @Schema(description = "功能ID集合")
    private List<Long> functionIds;

    @Schema(description = "数据集ID集合")
    private List<Long> datasetIds;
}
