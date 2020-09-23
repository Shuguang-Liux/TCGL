package com.record.tcgl;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.record"})
@EnableDubboConfiguration
@ImportResource(locations = "classpath:consumer.xml")
public class TcglWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglWebApplication.class, args);
    }

}
