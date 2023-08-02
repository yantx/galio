package com.galio.common.log.event;

import com.galio.core.utils.ObjectUtil;
import com.galio.system.api.AccessLogExchange;
import com.galio.system.api.OperLogExchange;
import com.galio.system.dto.AccessLogDto;
import com.galio.system.dto.OperLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步调用日志服务
 *
 * @author ruoyi
 */
@Component
@RequiredArgsConstructor
public class LogEventListener {

    private final OperLogExchange operLogExchange;
    private final AccessLogExchange accessLogExchange;

    /**
     * 保存系统日志记录
     */
    @Async
    @EventListener
    public void saveLog(OperLogEvent operLogEvent) {
        OperLogDto sysOperLog = ObjectUtil.copyObject(operLogEvent, OperLogDto.class);
        operLogExchange.add(sysOperLog);
    }

    @Async
    @EventListener
    public void saveAccessLog(AccessLogEvent accessLogEvent) {
        AccessLogDto accessLogDto = ObjectUtil.copyObject(accessLogEvent, AccessLogDto.class);
        accessLogExchange.add(accessLogDto);
    }

}
