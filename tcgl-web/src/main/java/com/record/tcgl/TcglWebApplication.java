package com.record.tcgl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Shuguang_Liux
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.record"})
@EnableDubbo
public class TcglWebApplication{

    public static void main(String[] args) {
        SpringApplication.run(TcglWebApplication.class, args);
        System.out.println("消费者启动成功！");
    }

}
