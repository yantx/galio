package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ocotpus
 * @Date: 2023-01-18
 * @Description: 数据集信息实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_dataset")
public class SysDataset {
    @TableId(value = "dataset_id")
    private long datasetId;
    private long datasourceId;
    private String datasetCode;
    private String datasetName;
    private String dataContent;
    private String fetchType;
    private String dataTable;
    private String primaryKey;
    private String labelCol;
    private String whereClause;
    private String orderCol;
    private long appId;
    private String remark;
    private long createBy;
    private Date createTime;
    private long updateBy;
    private Date updateTime;
}
