package com.tcgl.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tcgl.common.security.annotation.EnableCustomConfig;
import com.tcgl.common.security.annotation.EnableRyFeignClients;
import com.tcgl.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class TcglJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(TcglJobApplication.class, args);
        System.out.println("定时任务模块启动成功");
    }
}
