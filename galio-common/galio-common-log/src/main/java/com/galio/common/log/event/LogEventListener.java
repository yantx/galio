package com.galio.common.log.event;

import com.galio.core.utils.ObjectUtil;
import com.galio.system.api.LogininforExchange;
import com.galio.system.api.OperLogExchange;
import com.galio.system.dto.LogininforDto;
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
    private final LogininforExchange logininforExchange;

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
    public void saveLogininfor(LogininforEvent logininforEvent) {
        LogininforDto sysLogininfor = ObjectUtil.copyObject(logininforEvent, LogininforDto.class);
        logininforExchange.add(sysLogininfor);
    }

}
