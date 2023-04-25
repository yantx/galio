package com.galio.system.model.vo;

import lombok.Data;

/**
 * @Author: galio
 * @Date: 2023-04-25
 * @Description: 群组信息视图对象
 */
@Data
public class GroupVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long groupId;

    /**
     * 组代码
     */
    private Long groupCode;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 显示顺序
     */
    private Long orderNum;

    /**
     * 状态1正常 0异常
     */
    private String status;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 备注
     */
    private String remark;


}
