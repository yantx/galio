package com.galio.gen.controller;

import com.galio.core.enums.ResponseCodeEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.text.Convert;
import com.galio.gen.enums.GenExceptionResponseEnum;
import com.galio.gen.model.GenTable;
import com.galio.gen.model.GenTableColumn;
import com.galio.gen.service.IGenTableColumnService;
import com.galio.gen.service.IGenTableService;
import com.galio.mybatis.page.PageDto;
import com.galio.system.entity.SysOrg;
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
public class GenController{

    private final IGenTableService genTableService;
    private final IGenTableColumnService genTableColumnService;


    /**
     * 查询代码生成列表
     */
    @PostMapping("/list")
    public Object genList(GenTable genTable, PageDto pageQuery) {
        return genTableService.selectPageGenTableList(genTable, pageQuery);
    }

    /**
     * 修改代码生成业务
     *
     * @param tableId 表主键
     */
    @GetMapping(value = "/{tableId}")
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
    @GetMapping("/db/list")
    public Object dataList(GenTable genTable, PageDto pageQuery) {
        return genTableService.selectPageDbTableList(genTable, pageQuery);
    }

    /**
     * 查询数据表字段列表
     *
     * @param tableId 表主键
     */
    @GetMapping("/column/list")
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
    public void importTableSave(String tables){
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
//        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
//        genTableService.importGenTable(tableList);
        String str = null;
        System.out.println(str.equals(""));

//        throw new CustomException(GenExceptionResponseEnum.GEN_TABLE_IMPORT_ERROR);
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
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
    public void genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
    }

    /**
     * 同步数据库
     *
     * @param tableName 表名
     */
    @GetMapping("/synchDb/{tableName}")
    public void synchDb(@PathVariable("tableName") String tableName) {
        genTableService.syncDb(tableName);
    }

    /**
     * 批量生成代码
     *
     * @param tables 表名串
     */
    @GetMapping("/batchGenCode")
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
