package com.we.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author we
 * @date 2021-08-30 20:37
 **/
/*@ConditionalOnClass({RedisOperations.class})*/
@Configuration
public class WeConfiguration {
    @Bean
    public WeCoreServie weCoreServie(){
        return new WeCoreServie();
    }
}
