package com.tcgl.job;


/**
 * @package com.example.demo.task
 * @Description 定时调度配置类
 * @Editor Shuguang_Liux
 * @Date 2020/8/26 13:23
 **/
//@Component
//@Slf4j
//@EnableAsync
public class ScheduleTask {

    /**
     * cron表达式
     * 每3秒执行一次
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    @Async
//    public void run1() {
//        log.info("======cron1======");
//        System.err.println("定时调度开始1");
//    }
//    @Scheduled(cron = "0 0/1 * * * ?")
//    @Async
//    public void run2() {
//        log.info("======cron2======");
//        System.err.println("定时调度开始2");
//    }



    /**
     * 启动后10秒开始执行，固定5秒周期执行一次
     */
//    @Scheduled(initialDelay = 10000, fixedRate = 5000)
//    public void run2() {
//        log.info("======fixedRate======");
//    }
//
//    /**
//     * 启动后10秒开始执行，距离上次执行结束之后20秒再开始执行下一次
//     */
//    @Scheduled(initialDelay = 10000, fixedDelay = 20000)
//    public void run3() {
//        log.info("======fixedDelay======");
//    }
}
