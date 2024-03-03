package com.galio.mybatis.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-04-11 07:12:23
 * @Description: 分页查询结果
 */
@Data
@NoArgsConstructor
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageNumber;
    private Integer pageSize;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<T> rows;

    public PageVO(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public static <T> PageVO<T> build(IPage<T> page) {
        PageVO<T> rspData = new PageVO<>();
        if (CollectionUtils.isNotEmpty(page.getRecords()))
            rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }
    public static <T> PageVO<T> build(List<T> list) {
        PageVO<T> rspData = new PageVO<>();
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }
}
