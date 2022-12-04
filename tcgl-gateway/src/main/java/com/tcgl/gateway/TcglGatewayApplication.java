package com.tcgl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TcglGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TcglGatewayApplication.class, args);
        System.out.println("若依网关启动成功");
    }
}
