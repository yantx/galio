package com.galio.gen.controller;

import com.galio.core.text.Convert;
import com.galio.gen.model.GenTable;
import com.galio.gen.model.GenTableColumn;
import com.galio.gen.service.IGenTableColumnService;
import com.galio.gen.service.IGenTableService;
import com.galio.mybatis.page.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author txyan
 * @createDate 2023-02-15 21:13:37
 * @description 代码生成器接口
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/generate")
@RestController
@Tag(name = "代码生成器接口")
public class GenController{

    private final IGenTableService genTableService;
    private final IGenTableColumnService genTableColumnService;


    /**
     * 查询代码生成列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询代码生成列表")
    public Object genList(@RequestBody PageDto pageQuery) {
        return genTableService.selectPageGenTableList(pageQuery);
    }

    /**
     * 修改代码生成业务
     *
     * @param tableId 表主键
     */
    @GetMapping(value = "/{tableId}")
    @Operation(summary = "修改代码生成业务")
    public Object getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return map;
    }

    /**
     * 查询数据库列表
     */
    @PostMapping("/db/list")
    @Operation(summary = "查询数据库列表")
    public Object dataList(@RequestBody PageDto pageQuery) {
        return genTableService.selectPageDbTableList(pageQuery);
    }

    /**
     * 查询数据表字段列表
     *
     * @param tableId 表主键
     */
    @GetMapping("/column/list")
    @Operation(summary = "查询数据表字段列表")
    public Object columnList(Long tableId) {
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        return list;
    }

    /**
     * 导入表结构（保存）
     *
     * @param tables 表名串
     */
    @PostMapping("/importTable")
    @Operation(summary = "导入表结构")
    public void importTableSave(String tables){
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    @Operation(summary = "修改保存代码生成业务")
    public Object editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return null;
    }

    /**
     * 删除代码生成
     *
     * @param tableIds 表主键串
     */
    @DeleteMapping("/{tableIds}")
    @Operation(summary = "删除代码生成")
    public Object remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return null;
    }

    /**
     * 预览代码
     *
     * @param tableId 表主键
     */
    @GetMapping("/preview/{tableId}")
    @Operation(summary = "预览代码")
    public Object preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return dataMap;
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名
     */
    @GetMapping("/download/{tableName}")
    @Operation(summary = "生成代码下载")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名
     */
    @GetMapping("/genCode/{tableName}")
    @Operation(summary = "自定义路径生成代码")
    public void genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
    }

    /**
     * 同步数据库
     *
     * @param tableName 表名
     */
    @GetMapping("/synchDb/{tableName}")
    @Operation(summary = "同步数据库")
    public void synchDb(@PathVariable("tableName") String tableName) {
        genTableService.syncDb(tableName);
    }

    /**
     * 批量生成代码
     *
     * @param tables 表名串
     */
    @GetMapping("/batchGenCode")
    @Operation(summary = "批量生成代码")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"galio.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
