package com.galio.gen.mapper;

import com.galio.gen.model.GenTableColumn;
import com.galio.mybatis.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author txyan
* @description 针对表【gen_table_column(代码生成业务表字段)】的数据库操作Mapper
* @createDate 2023-02-15 21:13:37
* @Entity generator.domain.GenTableColumn
*/
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumnMapper, GenTableColumn> {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
}




