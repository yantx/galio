package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.galio.core.model.BaseEntity;

/**
 * @Author: galio
 * @Date: 2023-05-30
 * @Description: 数据源信息对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_datasource")
public class Datasource extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 数据源id
     */
    @TableId(value = "datasource_id")
    private Long datasourceId;
    /**
     * 数据源名称
     */
    private String datasourceName;
    /**
     * 数据源类型
     */
    private String datasourceType;
    /**
     * 链接驱动
     */
    private String driverClass;
    /**
     * 数据库链接
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 测试查询语句
     */
    private String testQuery;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 备注
     */
    private String remark;

}
