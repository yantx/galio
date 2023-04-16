package com.galio.system.model.vo;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-16
 * @Description: 数据集信息视图对象
 */
@Data
public class DatasetVo {

    private static final long serialVersionUID = 1L;

    /**
     * 数据集id
     */
    private Long datasetId;

    /**
     * 数据源id
     */
    private Long datasourceId;

    /**
     * 数据集代码
     */
    private String datasetCode;

    /**
     * 数据集名称
     */
    private String datasetName;

    /**
     * 获取数据内容
     */
    private String dataContent;

    /**
     * 获取数据方式
     */
    private String fetchType;

    /**
     * 数据表名
     */
    private String dataTable;

    /**
     * 主键列
     */
    private String primaryKey;

    /**
     * 显示名称
     */
    private String labelCol;

    /**
     * 过滤条件
     */
    private String whereClause;

    /**
     * 排序列名
     */
    private String orderCol;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 备注
     */
    private String remark;


}
