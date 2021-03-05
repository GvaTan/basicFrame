package com.zhys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinInvSapApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinInvSapApplication.class, args);
    }

}
