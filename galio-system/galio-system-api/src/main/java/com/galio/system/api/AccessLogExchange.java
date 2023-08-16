package com.galio.system.api;

import com.galio.system.dto.AccessLogDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @Author: galio
 * @Date: 2023-06-01 07:12:19
 * @Description: 系统访问日志信息交易接口
 */
@HttpExchange("system/access_log")
public interface AccessLogExchange {

    @PostExchange()
    Boolean add(@RequestBody AccessLogDto dto);
}
