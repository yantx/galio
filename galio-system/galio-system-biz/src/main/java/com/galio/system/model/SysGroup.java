package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 账号组实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_group")
public class SysGroup {
    @TableId(value = "group_id")
    private long groupId;
    private long groupCode;
    private String groupName;
    private long orderNum;
    private String status;
    private long appId;
    private String remark;
    private long createBy;
    private Date createTime;
    private long updateBy;
    private Date updateTime;
}
