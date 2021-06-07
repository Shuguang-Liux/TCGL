package com.record.tcgl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Shuguang_Liux
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@MapperScan("com.record.tcgl.dao")
public class TcglServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglServiceApplication.class, args);
        System.out.println("服务者启动成功");
    }

}
