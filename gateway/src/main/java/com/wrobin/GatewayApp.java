package com.wrobin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * created by robin.wu on 2018/7/23
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.wrobin"})
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class,args);
    }
}
