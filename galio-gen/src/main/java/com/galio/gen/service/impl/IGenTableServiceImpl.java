package com.galio.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.core.exception.CustomException;
import com.galio.core.text.CharsetKit;
import com.galio.core.utils.JsonUtils;
import com.galio.core.utils.StreamUtils;
import com.galio.core.utils.StringUtil;
import com.galio.gen.constant.GenConstants;
import com.galio.gen.model.GenTable;
import com.galio.gen.model.GenTableColumn;
import com.galio.gen.enums.GenExceptionResponseEnum;
import com.galio.gen.mapper.GenTableColumnMapper;
import com.galio.gen.mapper.GenTableMapper;
import com.galio.gen.service.IGenTableService;
import com.galio.gen.util.GenUtils;
import com.galio.gen.util.VelocityInitializer;
import com.galio.gen.util.VelocityUtils;
import com.galio.mybatis.page.PageDto;
import com.galio.mybatis.page.PageVo;
import com.galio.satoken.utils.LoginHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



/**
 * @author galio
 * @description 针对表【gen_table(代码生成表)】的数据库操作Service实现
 * @createDate 2023-02-15 21:13:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IGenTableServiceImpl implements IGenTableService {

    private final GenTableMapper genTableMapper;
    private final GenTableColumnMapper genTableColumnMapper;

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(Long id) {
        GenTable genTable = genTableMapper.selectGenTableById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    @Override
    public PageVo<GenTable> selectPageGenTableList(PageDto pageQuery) {
        return PageVo.build(genTableMapper.selectPage(pageQuery.build(),buildGenTableQueryWrapper(new GenTable())));
    }

    private QueryWrapper<GenTable> buildGenTableQueryWrapper(GenTable genTable) {
        Map<String, Object> params = genTable.getParams();
        QueryWrapper<GenTable> wrapper = Wrappers.query();
        wrapper.like(StringUtil.isNotBlank(genTable.getTableName()), "lower(table_name)" , StringUtil.lowerCase(genTable.getTableName()))
                .like(StringUtil.isNotBlank(genTable.getTableComment()), "lower(table_comment)" , StringUtil.lowerCase(genTable.getTableComment()))
                .between(params.get("beginTime") != null && params.get("endTime") != null,
                        "create_time" , params.get("beginTime"), params.get("endTime"));
        return wrapper;
    }


    @Override
    public PageVo<GenTable> selectPageDbTableList(PageDto pageQuery) {
        return PageVo.build(genTableMapper.selectPageDbTableList(pageQuery.build(), new GenTable()));
    }

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    @Override
    public List<GenTable> selectGenTableAll() {
        return genTableMapper.selectGenTableAll();
    }

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGenTable(GenTable genTable) {
        String options = JsonUtils.toString(genTable.getParams());
        genTable.setOptions(options);
        int row = genTableMapper.updateById(genTable);
        if (row > 0) {
            for (GenTableColumn cenTableColumn : genTable.getColumns()) {
                genTableColumnMapper.updateById(cenTableColumn);
            }
        }
    }

    /**
     * 删除业务对象
     *
     * @param tableIds 需要删除的数据ID
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGenTableByIds(Long[] tableIds) {
        List<Long> ids = Arrays.asList(tableIds);
        genTableMapper.deleteBatchIds(ids);
        genTableColumnMapper.delete(new LambdaQueryWrapper<GenTableColumn>().in(GenTableColumn::getTableId, ids));
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importGenTable(List<GenTable> tableList) {
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, 1L);
                int row = genTableMapper.insert(table);
                if (row > 0) {
                    // 保存列信息
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
                    List<GenTableColumn> saveColumns = new ArrayList<>();
                    for (GenTableColumn column : genTableColumns) {
                        GenUtils.initColumnField(column, table);
                        saveColumns.add(column);
                    }
                    if (CollectionUtils.isNotEmpty(saveColumns)) {
                        genTableColumnMapper.insertBatch(saveColumns);
                    }
                }
            }
        } catch (Exception e) {
            log.error("gen table import error: ", e);
            throw new CustomException(GenExceptionResponseEnum.GEN_TABLE_IMPORT_ERROR);
        }
    }

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long tableId) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTable table = genTableMapper.selectGenTableById(tableId);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetKit.UTF_8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名称
     */
    @Override
    public void generatorCode(String tableName) {
        // 查询表信息
        GenTable table = genTableMapper.selectGenTableByName(tableName);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            if (!StringUtil.containsAny(template, "sql.vm" , "api.js.vm" , "index.vue.vm" , "index-tree.vue.vm")) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, CharsetKit.UTF_8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), CharsetKit.UTF_8);
                } catch (Exception e) {
                    log.error("{} gen template write error: {}", table.getTableName(), e.getMessage());
                    throw new CustomException(GenExceptionResponseEnum.GEN_TEMPLATE_WRITE_ERROR.packageByArgs(table.getTableName()));
                }
            }
        }
    }

    /**
     * 同步数据库
     *
     * @param tableName 表名称
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void syncDb(String tableName) {
        GenTable table = genTableMapper.selectGenTableByName(tableName);
        List<GenTableColumn> tableColumns = table.getColumns();
        Map<String, GenTableColumn> tableColumnMap = StreamUtils.toIdentityMap(tableColumns, GenTableColumn::getColumnName);

        List<GenTableColumn> dbTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
        if (CollectionUtils.isEmpty(dbTableColumns)) {
            log.error("sync db fail, {} table struct not found", tableName);
            throw new CustomException(GenExceptionResponseEnum.GEN_TABLE_STRUCT_NOT_FOUND.packageByArgs(tableName));
        }
        List<String> dbTableColumnNames = StreamUtils.toList(dbTableColumns, GenTableColumn::getColumnName);

        List<GenTableColumn> saveColumns = new ArrayList<>();
        dbTableColumns.forEach(column -> {
            GenUtils.initColumnField(column, table);
            if (tableColumnMap.containsKey(column.getColumnName())) {
                GenTableColumn prevColumn = tableColumnMap.get(column.getColumnName());
                column.setColumnId(prevColumn.getColumnId());
                if (column.isList()) {
                    // 如果是列表，继续保留查询方式/字典类型选项
                    column.setDictType(prevColumn.getDictType());
                    column.setQueryType(prevColumn.getQueryType());
                }
                if (StringUtil.isNotEmpty(prevColumn.getIsRequired()) && !column.isPk()
                        && (column.isInsert() || column.isEdit())
                        && ((column.isUsableColumn()) || (!column.isSuperColumn()))) {
                    // 如果是(新增/修改&非主键/非忽略及父属性)，继续保留必填/显示类型选项
                    column.setIsRequired(prevColumn.getIsRequired());
                    column.setHtmlType(prevColumn.getHtmlType());
                }
                genTableColumnMapper.updateById(column);
            } else {
                genTableColumnMapper.insert(column);
            }
        });
        if (CollectionUtils.isNotEmpty(saveColumns)) {
            genTableColumnMapper.insertBatch(saveColumns);
        }

        List<GenTableColumn> delColumns = StreamUtils.filter(tableColumns, column -> !dbTableColumnNames.contains(column.getColumnName()));
        if (CollectionUtils.isNotEmpty(delColumns)) {
            List<Long> ids = StreamUtils.toList(delColumns, GenTableColumn::getColumnId);
            genTableColumnMapper.deleteBatchIds(ids);
        }
    }

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        GenTable table = genTableMapper.selectGenTableByName(tableName);

        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetKit.UTF_8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, StandardCharsets.UTF_8);
                IOUtils.close(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    @Override
    public void validateEdit(GenTable genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
            String options = JsonUtils.toString(genTable.getParams());
            LinkedHashMap paramsObj = JsonUtils.toObject(options, LinkedHashMap.class);
            if (StringUtil.isEmpty(paramsObj.get(GenConstants.TREE_CODE).toString())) {
                log.error(GenExceptionResponseEnum.GEN_TREE_CODE_NOT_NULL.getMsg());
                throw new CustomException(GenExceptionResponseEnum.GEN_TREE_CODE_NOT_NULL);
            } else if (StringUtil.isEmpty(paramsObj.get(GenConstants.TREE_PARENT_CODE).toString())) {
                log.error(GenExceptionResponseEnum.GEN_TREE_PARENT_CODE_NOT_NULL.getMsg());
                throw new CustomException(GenExceptionResponseEnum.GEN_TREE_PARENT_CODE_NOT_NULL);
            } else if (StringUtil.isEmpty(paramsObj.get(GenConstants.TREE_NAME).toString())) {
                log.error(GenExceptionResponseEnum.GEN_TREE_NAME_NOT_NULL.getMsg());
                throw new CustomException(GenExceptionResponseEnum.GEN_TREE_NAME_NOT_NULL);
            } else if (GenConstants.TPL_SUB.equals(genTable.getTplCategory())) {
                if (StringUtil.isEmpty(genTable.getSubTableName())) {
                    log.error(GenExceptionResponseEnum.GEN_SUB_TABLE_NOT_NULL.getMsg());
                    throw new CustomException(GenExceptionResponseEnum.GEN_SUB_TABLE_NOT_NULL);
                } else if (StringUtil.isEmpty(genTable.getSubTableFkName())) {
                    log.error(GenExceptionResponseEnum.GEN_SUB_TABLE_FK_NAME_NOT_NULL.getMsg());
                    throw new CustomException(GenExceptionResponseEnum.GEN_SUB_TABLE_FK_NAME_NOT_NULL);
                }
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (ObjectUtils.isEmpty(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if (GenConstants.TPL_SUB.equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if (column.isPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (ObjectUtils.isEmpty(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }

    /**
     * 设置主子表信息
     *
     * @param table 业务表信息
     */
    public void setSubTable(GenTable table) {
        String subTableName = table.getSubTableName();
        if (StringUtil.isNotEmpty(subTableName)) {
            table.setSubTable(genTableMapper.selectGenTableByName(subTableName));
        }
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable) {
        LinkedHashMap paramsObj = JsonUtils.toObject(genTable.getOptions(), LinkedHashMap.class);
        if (ObjectUtils.isNotEmpty(paramsObj)) {
            String treeCode = paramsObj.get(GenConstants.TREE_CODE).toString();
            String treeParentCode = paramsObj.get(GenConstants.TREE_PARENT_CODE).toString();
            String treeName = paramsObj.get(GenConstants.TREE_NAME).toString();

            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(GenTable table, String template) {
        String genPath = table.getGenPath();
        if (StringUtil.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }
}



