package com.we;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.we.mapper")
@SpringBootApplication
public class SpringbootExcelPoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExcelPoiApplication.class, args);
    }

}
