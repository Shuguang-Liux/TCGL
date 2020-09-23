package com.record.tcgl;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource(locations={"classpath:provider.xml"})
@EnableTransactionManagement
@EnableDubboConfiguration
@MapperScan("com.record.tcgl.dao")
public class TcglServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglServiceApplication.class, args);
    }

}
