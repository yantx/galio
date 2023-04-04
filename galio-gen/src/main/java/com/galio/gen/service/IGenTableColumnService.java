package com.galio.gen.service;


import com.galio.gen.model.GenTableColumn;

import java.util.List;

/**
* @author txyan
* @description 针对表【gen_table_column(代码生成业务表字段)】的数据库操作Service
* @createDate 2023-02-15 21:13:37
*/
public interface IGenTableColumnService {
    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);
}
