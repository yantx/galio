package com.galio.gen.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.gen.model.GenTable;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;

import java.util.List;
import java.util.Map;

/**
* @author txyan
* @description 针对表【gen_table(代码生成表)】的数据库操作Service
* @createDate 2023-02-15 21:13:37
*/
public interface IGenTableService {

    PageVo<GenTable> selectPageGenTableList(PageDto pageQuery);

    PageVo<GenTable> selectPageDbTableList(PageDto pageQuery);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    void updateGenTable(GenTable genTable);

    /**
     * 删除业务信息
     *
     * @param tableIds 需要删除的表数据ID
     * @return 结果
     */
    void deleteGenTableByIds(Long[] tableIds);

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    void importGenTable(List<GenTable> tableList);

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] downloadCode(String tableName);

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名称
     * @return 数据
     */
    void generatorCode(String tableName);

    /**
     * 同步数据库
     *
     * @param tableName 表名称
     */
    void syncDb(String tableName);

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] downloadCode(String[] tableNames);

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    void validateEdit(GenTable genTable);

}
