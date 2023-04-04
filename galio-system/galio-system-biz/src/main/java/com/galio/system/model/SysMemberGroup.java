package com.galio.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: galio
 * @Date: 2023-01-18
 * @Description: 人员与群组关联实体类
 */
@Data
@NoArgsConstructor
@TableName("sys_member_group")
public class SysMemberGroup {
    private long memberId;
    private long groupId;
}
