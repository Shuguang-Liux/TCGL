package com.tcgl.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tcgl.common.security.annotation.EnableCustomConfig;
import com.tcgl.common.security.annotation.EnableRyFeignClients;
import com.tcgl.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class TcglSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TcglSystemApplication.class, args);
        System.out.println("系统模块启动成功");
    }
}
