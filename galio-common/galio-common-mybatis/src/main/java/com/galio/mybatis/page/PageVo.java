package com.galio.mybatis.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-11 07:12:23
 * @Description: 分页查询结果
 */
@Data
@NoArgsConstructor
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<T> rows;

    public PageVo(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public static <T> PageVo<T> build(IPage<T> page) {
        PageVo<T> rspData = new PageVo<>();
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }

    public static <T> PageVo<T> build(List<T> list) {
        PageVo<T> rspData = new PageVo<>();
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }
}
