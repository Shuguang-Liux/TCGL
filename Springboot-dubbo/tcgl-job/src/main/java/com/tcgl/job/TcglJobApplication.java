package com.tcgl.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Shuguang_Liux
 */
@EnableScheduling
@SpringBootApplication
public class TcglJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglJobApplication.class, args);
    }

}
