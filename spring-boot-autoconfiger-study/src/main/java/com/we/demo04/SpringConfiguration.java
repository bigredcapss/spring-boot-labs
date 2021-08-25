package com.we.demo04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author we
 * @date 2021-08-25 11:39
 **/
@Configuration
public class SpringConfiguration {

    /**
     * @Conditional(CustomerCondition.class)
     * 意味只有CustomerCondition的match方法返回true时，才能返回DemoService的实例
     * @return
     */
    @Conditional(CustomerCondition.class)
    @Bean
    public DemoService demoService(){
        return new DemoService();
    }

}
