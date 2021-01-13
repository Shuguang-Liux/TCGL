package com.record.tcgl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Shuguang_Liux
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.record"})
//@EnableDubboConfiguration
//@ImportResource(locations = "classpath:consumer.xml")
@EnableDubbo
public class TcglWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglWebApplication.class, args);
    }

}
