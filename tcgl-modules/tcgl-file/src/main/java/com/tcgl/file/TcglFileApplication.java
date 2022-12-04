package com.tcgl.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.tcgl.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 *
 * @author tcgl
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TcglFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(TcglFileApplication.class, args);
        System.out.println("文件服务模块启动成功");
    }
}
