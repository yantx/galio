package com.galio.common.log.event;

import com.galio.core.utils.ObjectUtil;
import com.galio.system.api.AccessLogExchange;
import com.galio.system.api.OperLogExchange;
import com.galio.system.dto.AccessLogDTO;
import com.galio.system.dto.OperLogDTO;
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
        OperLogDTO operLogDTO = ObjectUtil.copyObject(operLogEvent, OperLogDTO.class);
        operLogExchange.add(operLogDTO);
    }

    @Async
    @EventListener
    public void saveAccessLog(AccessLogEvent accessLogEvent) {
        AccessLogDTO accessLogDTO = ObjectUtil.copyObject(accessLogEvent, AccessLogDTO.class);
        accessLogExchange.add(accessLogDTO);
    }

}
