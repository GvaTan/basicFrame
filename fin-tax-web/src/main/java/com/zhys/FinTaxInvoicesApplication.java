package com.zhys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinTaxInvoicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinTaxInvoicesApplication.class, args);
    }

}
