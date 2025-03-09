package com.we.springbootjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.we.springbootjwt.mapper")
public class SpringbootJwtApplication
{
    public static void main (String[] args)
    {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }

}
