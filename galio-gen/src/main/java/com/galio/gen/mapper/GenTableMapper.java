package com.galio.gen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.gen.model.GenTable;
import com.galio.mybatis.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author txyan
* @description 针对表【gen_table(代码生成表)】的数据库操作Mapper
* @createDate 2023-02-15 21:13:37
* @Entity generator.domain.GenTable
*/
public interface GenTableMapper extends BaseMapperPlus<GenTableMapper, GenTable> {

    Page<GenTable> selectPageDbTableList(@Param("page") Page<GenTable> page, @Param("genTable") GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(@Param("tableNames") String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);

}




