package com.galio.system.api;

import com.galio.core.validate.InsertGroup;
import com.galio.system.dto.OperLogDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @Author: galio
 * @Date: 2023-05-31 08:10:20
 * @Description: 操作日志相关交易接口
 */
@HttpExchange("/operLog")
public interface OperLogExchange {
    @PostExchange()
    Boolean add(@Validated(InsertGroup.class) @RequestBody OperLogDto dto);
}
