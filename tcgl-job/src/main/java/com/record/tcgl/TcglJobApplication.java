package com.record.tcgl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TcglJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcglJobApplication.class, args);
    }

}
