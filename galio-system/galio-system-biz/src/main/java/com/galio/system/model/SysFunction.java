package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 功能实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_function")
public class SysFunction {
    @TableId(value = "function_id")
    private long functionId;
    private String functionName;
    private long parentId;
    private long orderNum;
    private String path;
    private String component;
    private String queryParam;
    private String isFrame;
    private String isCache;
    private String functionType;
    private String belong;
    private String status;
    private String visible;
    private String deleteFlag;
    private String perms;
    private String icon;
    private long appId;
    private long createBy;
    private Date createTime;
    private long updateBy;
    private Date updateTime;
    private String remark;
}
