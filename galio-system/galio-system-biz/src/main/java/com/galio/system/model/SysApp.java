package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 应用实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_app")
public class SysApp {
  @TableId(value = "app_id")
  private long appId;
  private String appName;
  private String appCode;
  private String appIcon;
  private String isFixed;
  private String status;
  private String remark;
  private long createBy;
  private Date createTime;
  private long updateBy;
  private Date updateTime;

}
