package com.wrobin.user;

import com.wrobin.user.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * created by robin.wu on 2018/3/14
 **/
@SpringBootApplication
@MapperScan("com.wrobin.user.dao.mapper")
@ServletComponentScan("com.wrobin.parent.config")
@EnableTransactionManagement
public class UserApp implements CommandLineRunner{
    @Autowired
    private UserMapper userMapper;
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class,args);
    }

    @Transactional
    public void run(String... strings) throws Exception {


    }
}
