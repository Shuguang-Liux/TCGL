package com.tcgl.common.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.tcgl.common.core.constant.SecurityConstants;
import com.tcgl.system.api.RemoteLogService;
import com.tcgl.system.api.domain.SysOperLog;

import javax.annotation.Resource;

/**
 * 异步调用日志服务
 *
 * @author tcgl
 */
@Service
public class AsyncLogService {
    @Resource
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
