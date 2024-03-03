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
public class DatasetDTO extends BaseEntity {

    /**
     * 数据集id
     */
    @Schema(description = "数据集id")
    private Long datasetId;

    /**
     * 数据源id
     */
    @Schema(description = "数据源id")
    private Long datasourceId;

    /**
     * 数据集代码
     */
    @Schema(description = "数据集代码")
    private String datasetCode;

    /**
     * 数据集名称
     */
    @Schema(description = "数据集名称")
    private String datasetName;

    /**
     * 获取数据内容
     */
    @Schema(description = "获取数据内容")
    private String dataContent;

    /**
     * 获取数据方式
     */
    @Schema(description = "获取数据方式")
    private String fetchType;

    /**
     * 数据表名
     */
    @Schema(description = "数据表名")
    private String dataTable;

    /**
     * 主键列
     */
    @Schema(description = "主键列")
    private String primaryKey;

    /**
     * 显示名称
     */
    @Schema(description = "显示名称")
    private String labelCol;

    /**
     * 过滤条件
     */
    @Schema(description = "过滤条件")
    private String whereClause;

    /**
     * 排序列名
     */
    @Schema(description = "排序列名")
    private String orderCol;

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


}
