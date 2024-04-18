package com.sports;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("com.sports.dao")
public class SportApplication  {
    public static void main(String[] args) {
        SpringApplication.run(SportApplication.class, args);
    }

}
