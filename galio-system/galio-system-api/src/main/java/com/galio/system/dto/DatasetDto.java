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
 * @Description: 数据集信息业务对象
 */

@Data
@Schema(description = "数据集信息业务对象")
public class DatasetDto extends BaseEntity {

    /**
     * 数据集id
     */
    @NotNull(message = "数据集id不能为空", groups = { UpdateGroup.class })
    @Schema(description = "数据集id")
    private Long datasetId;

    /**
     * 数据源id
     */
    @NotNull(message = "数据源id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据源id")
    private Long datasourceId;

    /**
     * 数据集代码
     */
    @NotBlank(message = "数据集代码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据集代码")
    private String datasetCode;

    /**
     * 数据集名称
     */
    @NotBlank(message = "数据集名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据集名称")
    private String datasetName;

    /**
     * 获取数据内容
     */
    @NotBlank(message = "获取数据内容不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "获取数据内容")
    private String dataContent;

    /**
     * 获取数据方式
     */
    @NotBlank(message = "获取数据方式不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "获取数据方式")
    private String fetchType;

    /**
     * 数据表名
     */
    @NotBlank(message = "数据表名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "数据表名")
    private String dataTable;

    /**
     * 主键列
     */
    @NotBlank(message = "主键列不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "主键列")
    private String primaryKey;

    /**
     * 显示名称
     */
    @NotBlank(message = "显示名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "显示名称")
    private String labelCol;

    /**
     * 过滤条件
     */
    @NotBlank(message = "过滤条件不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "过滤条件")
    private String whereClause;

    /**
     * 排序列名
     */
    @NotBlank(message = "排序列名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    @Schema(description = "排序列名")
    private String orderCol;

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
