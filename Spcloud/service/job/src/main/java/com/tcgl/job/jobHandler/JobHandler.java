package com.tcgl.job.jobHandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author Shuguang_Liux
 * @date 2021/11/10 16:46
 */
@Component
public class JobHandler{


    @XxlJob("testJob")
    public ReturnT<String> ex(String param) {
        System.out.println("测试定时任务开始");
        return ReturnT.SUCCESS;
    }
}
