package com.galio.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.galio.gen.model.GenTableColumn;
import com.galio.gen.mapper.GenTableColumnMapper;
import com.galio.gen.service.IGenTableColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author txyan
* @description 针对表【gen_table_column(代码生成业务表字段)】的数据库操作Service实现
* @createDate 2023-02-15 21:13:37
*/
@RequiredArgsConstructor
@Service
public class IGenTableColumnServiceImpl implements IGenTableColumnService {
    private final GenTableColumnMapper genTableColumnMapper;
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
        return genTableColumnMapper.selectList(new LambdaQueryWrapper<GenTableColumn>()
                .eq(GenTableColumn::getTableId, tableId)
                .orderByAsc(GenTableColumn::getOrderNum));
    }
}




