package com.yewen.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yewen.shiro.dao")
public class SpringbootThymeleafShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootThymeleafShiroApplication.class, args);
    }

}
