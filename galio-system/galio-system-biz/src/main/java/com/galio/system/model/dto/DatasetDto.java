package com.galio.system.model.dto;

import com.galio.core.model.BaseEntity;
import com.galio.core.validate.InsertGroup;
import com.galio.core.validate.UpdateGroup;
import lombok.Data;
import jakarta.validation.constraints.*;


/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据集信息业务对象
 */

@Data
public class DatasetDto extends BaseEntity {

    /**
     * 数据集id
     */
    @NotNull(message = "数据集id不能为空", groups = { UpdateGroup.class })
    private Long datasetId;

    /**
     * 数据源id
     */
    @NotNull(message = "数据源id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long datasourceId;

    /**
     * 数据集代码
     */
    @NotBlank(message = "数据集代码不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String datasetCode;

    /**
     * 数据集名称
     */
    @NotBlank(message = "数据集名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String datasetName;

    /**
     * 获取数据内容
     */
    @NotBlank(message = "获取数据内容不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String dataContent;

    /**
     * 获取数据方式
     */
    @NotBlank(message = "获取数据方式不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String fetchType;

    /**
     * 数据表名
     */
    @NotBlank(message = "数据表名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String dataTable;

    /**
     * 主键列
     */
    @NotBlank(message = "主键列不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String primaryKey;

    /**
     * 显示名称
     */
    @NotBlank(message = "显示名称不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String labelCol;

    /**
     * 过滤条件
     */
    @NotBlank(message = "过滤条件不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String whereClause;

    /**
     * 排序列名
     */
    @NotBlank(message = "排序列名不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String orderCol;

    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private Long appId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { InsertGroup.class, UpdateGroup.class })
    private String remark;


}
