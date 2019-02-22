package com.taihe.databasedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taihe.databasedemo.dao")
public class DatabasedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabasedemoApplication.class, args);
    }

}

