package com.galio.system.api;

import com.galio.system.model.SysDictItem;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-01-13
 * @Description: 字典项相关业务交易接口
 */
@HttpExchange("/dict_item")
public interface DictItemExchange {
    /**
     * 分隔符
     */
    String SEPARATOR = ",";

    /**
     * 根据字典Id查询字典项数据
     *
     * @param dictId 字典ID
     * @return 字典项集合信息
     */

    List<SysDictItem> getDictItemByDictId(String dictId);

    /**
     * 根据字典编号查询字典项数据
     *
     * @param dictCode 字典编号
     * @return 字典项集合信息
     */
    List<SysDictItem> getDictItemByDictCode(String dictCode);

    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictId  字典ID
     * @param dictItemValue 字典项值
     * @return 字典标签
     */
    default String getDictItemLabel(String dictId, String dictItemValue) {
        return getDictItemLabel(dictId, dictItemValue, SEPARATOR);
    }

    /**
     * 根据字典和字典项标签获取字典值
     *
     * @param dictId 字典类型
     * @param dictItemLabel 字典标签
     * @return 字典值
     */
    default String getDictItemValue(String dictId, String dictItemLabel) {
        return getDictItemValue(dictId, dictItemLabel, SEPARATOR);
    }

    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictId  字典ID
     * @param dictItemValue 字典项值
     * @param separator 分隔符
     * @return 字典标签
     */
    String getDictItemLabel(String dictId, String dictItemValue, String separator);

    /**
     * 根据字典类型和字典标签获取字典值
     *
     * @param dictId  字典ID
     * @param dictItemLabel 字典项标签
     * @param separator 分隔符
     * @return 字典项值
     */
    String getDictItemValue(String dictId, String dictItemLabel, String separator);
}
