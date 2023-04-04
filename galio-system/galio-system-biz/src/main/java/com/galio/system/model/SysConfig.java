package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 参数配置实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_config")
public class SysConfig {
    @TableId(value = "config_id")
    private long configId;
    private String configName;
    private String configKey;
    private String configValue;
    private String configType;
    private long createBy;
    private Date createTime;
    private long updateBy;
    private Date updateTime;
    private String remark;
    private long appId;
}
