package com.galio.generator.mapper;

import com.galio.generator.model.TableColumn;
import com.galio.generator.model.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2024-02-06 22:05:09
 * @Description: 代码生成器用到的表以及字段信息查询
 */
@Mapper
public interface GeneratorMapper {

    /**
     * 查询表信息 列表
     *
     * @param tableNames
     * @return
     */
    List<TableInfo> selectDbTableListByNames(@Param("tableNames") List<String> tableNames);
    TableInfo selectTableByName(@Param("tableName") String tableName);
    List<TableColumn> selectDbTableColumnsByName(@Param("tableName") String tableName);



}
