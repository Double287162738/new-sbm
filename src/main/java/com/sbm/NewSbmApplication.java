package com.sbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.sbm.mapper")
public class NewSbmApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewSbmApplication.class, args);
    }
}
