package com.we.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author we
 * @date 2021-08-25 11:10
 **/
@Configuration
public class MyBatisConfiguration {

    @Bean
    public WeSqlSesssionFactory weSqlSesssionFactory(){
        return new WeSqlSesssionFactory();
    }
}
