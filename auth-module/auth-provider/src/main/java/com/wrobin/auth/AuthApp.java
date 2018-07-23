package com.wrobin.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.wrobin.auth"})
@MapperScan("com.wrobin.auth.dao.mapper")
@ServletComponentScan("com.wrobin.parent.config")
@EnableTransactionManagement
public class AuthApp{
    public static void main( String[] args ){
        SpringApplication.run(AuthApp.class,args);
    }
}
