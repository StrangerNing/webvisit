package com.webvisit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.webvisit.dao")
public class WebvisitApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebvisitApplication.class, args);
    }

}
