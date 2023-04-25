package com.galio.system.model.vo;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 数据源信息视图对象
 */
@Data
public class DatasourceVo {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源id
     */
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
