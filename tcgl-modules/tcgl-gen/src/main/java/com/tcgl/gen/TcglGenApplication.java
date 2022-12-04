package com.tcgl.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tcgl.common.security.annotation.EnableCustomConfig;
import com.tcgl.common.security.annotation.EnableRyFeignClients;
import com.tcgl.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class TcglGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(TcglGenApplication.class, args);
        System.out.println("代码生成模块启动成功");
    }
}
