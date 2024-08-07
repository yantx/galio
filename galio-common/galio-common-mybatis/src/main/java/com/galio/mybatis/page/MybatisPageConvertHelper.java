package com.galio.mybatis.page;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galio.core.constant.CommonConstants;
import com.galio.core.enums.ResponseEnum;
import com.galio.core.exception.CustomException;
import com.galio.core.model.PageRequestDTO;
import com.galio.core.utils.ObjectUtil;
import com.galio.core.utils.SqlUtil;
import com.galio.core.utils.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: galio
 * @Date: 2023-07-05 22:43:28
 * @Description: 分页助手
 */
public class MybatisPageConvertHelper {

    /**
     * 当前记录起始索引 默认值
     */
    public static final int DEFAULT_CURRENT_PAGE = 1;

    /**
     * 每页显示记录数 默认值 默认查全部
     */
    public static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;

    public static <T> Page<T> build(PageRequestDTO pageRequestDTO) {
        Integer pageNum = ObjectUtil.defaultIfNull(pageRequestDTO.getPageNumber(), DEFAULT_CURRENT_PAGE);
        Integer pageSize = ObjectUtil.defaultIfNull(pageRequestDTO.getPageSize(), DEFAULT_PAGE_SIZE);
        if (pageNum <= 0) {
            pageNum = DEFAULT_CURRENT_PAGE;
        }
        Page<T> page = new Page<>(pageNum, pageSize);
        List<OrderItem> orderItems = buildOrderItem(pageRequestDTO);
        if (CollectionUtils.isNotEmpty(orderItems)) {
            page.addOrder(orderItems);
        }
        return page;
    }

    /**
     * 构建排序
     *
     * 支持的用法如下:
     * {sortOrder:"asc",sortColumn:"id"} order by id asc
     * {sortOrder:"asc",sortColumn:"id,createTime"} order by id asc,create_time asc
     * {sortOrder:"desc",sortColumn:"id,createTime"} order by id desc,create_time desc
     * {sortOrder:"asc,desc",sortColumn:"id,createTime"} order by id asc,create_time desc
     */
    private static List<OrderItem> buildOrderItem(PageRequestDTO pageRequestDTO) {
        if (StringUtils.isBlank(pageRequestDTO.getSortColumn()) || StringUtils.isBlank(pageRequestDTO.getSortOrder())) {
            return null;
        }
        String orderBy = SqlUtil.escapeOrderBySql(pageRequestDTO.getSortColumn());
        orderBy = StringUtil.humpToLine(orderBy);

        String sortOrder;
        // 兼容前端排序类型
        sortOrder = StringUtils.replaceEach(pageRequestDTO.getSortOrder(), new String[]{"ascend", "descend"}, new String[]{"asc", "desc"});
        pageRequestDTO.setSortOrder(sortOrder);
        String[] orderByArr = orderBy.split(",");
        String[] sortOrderArr = pageRequestDTO.getSortOrder().split(",");
        if (sortOrderArr.length != 1 && sortOrderArr.length != orderByArr.length) {
            throw new CustomException(ResponseEnum.VALIDATE_ERROR);
        }

        List<OrderItem> list = new ArrayList<>();
        // 每个字段各自排序
        for (int i = 0; i < orderByArr.length; i++) {
            String orderByStr = orderByArr[i];
            String sortOrderStr = sortOrderArr.length == 1 ? sortOrderArr[0] : sortOrderArr[i];
            if (CommonConstants.ORDER_ASC.equals(sortOrderStr)) {
                list.add(OrderItem.asc(orderByStr));
            } else if (CommonConstants.ORDER_DESC.equals(sortOrderStr)) {
                list.add(OrderItem.desc(orderByStr));
            } else {
                throw new CustomException(ResponseEnum.ORDER_VALIDATE_ERROR);
            }
        }
        return list;
    }
}
