package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 数据源实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_datasource")
public class SysDatasource {
    @TableId(value = "datasource_id")
    private long datasourceId;
    private String datasourceName;
    private String datasourceType;
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String testQuery;
    private long appId;
    private String remark;
    private long createBy;
    private Date createTime;
    private long updateBy;
    private Date updateTime;
}
