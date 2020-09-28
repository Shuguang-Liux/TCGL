package com.record.tcgl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Shuguang_Liux
 */
@SpringBootApplication
@EnableDubbo
public class TcglWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglWebserviceApplication.class, args);
        System.out.println("启动成功");
    }

}
