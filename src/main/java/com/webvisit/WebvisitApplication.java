package com.webvisit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.webvisit.dao")
public class WebvisitApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebvisitApplication.class, args);
    }

}
