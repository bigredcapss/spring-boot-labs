package com.we.demo02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author we
 * @date 2021-08-25 11:13
 **/
@Configuration
public class RedisConfiguration {

    @Bean
    public WeRedisTemplate weRedisTemplate(){
        return new WeRedisTemplate();
    }
}
