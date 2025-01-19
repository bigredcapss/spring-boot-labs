package com.we.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author we
 * @date 2021-04-29 18:24
 **/
@Configuration
@Import({UserService.class})
public class JavaConfig {

    @Bean
    public UserService getUserSerivce(){
        return new UserService();
    }
}
