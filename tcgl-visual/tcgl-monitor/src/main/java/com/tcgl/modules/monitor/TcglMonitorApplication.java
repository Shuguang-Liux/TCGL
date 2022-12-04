package com.tcgl.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 *
 * @author tcgl
 */
@EnableAdminServer
@SpringBootApplication
public class TcglMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TcglMonitorApplication.class, args);
        System.out.println("监控中心启动成功");
    }
}
