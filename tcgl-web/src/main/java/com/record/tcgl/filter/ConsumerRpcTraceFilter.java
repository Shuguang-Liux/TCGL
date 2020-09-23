package com.record.tcgl.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author : sunmingyao
 * @since : 2019/12/4 16:39
 * <p>说明<br>
 */
@Activate(group = {Constants.CONSUMER})
public class ConsumerRpcTraceFilter implements Filter {
    /**
     *
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = MDC.get("traceId");
        if (StringUtils.isBlank(traceId)) {
            traceId = this.getUUID() ;
        }
        MDC.put("traceId",traceId);

        RpcContext.getContext().setAttachment("trace_id", traceId);
        return invoker.invoke(invocation);
    }

    /**
     * 获取UUID
     * @return String UUID
     */
    public String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //替换-字符
        return uuid.replaceAll("-", "");
    }

}
