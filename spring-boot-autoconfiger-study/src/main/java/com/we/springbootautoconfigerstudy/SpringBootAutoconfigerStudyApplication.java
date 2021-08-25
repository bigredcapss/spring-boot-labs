package com.we.springbootautoconfigerstudy;

import com.we.demo01.WeSqlSesssionFactory;
import com.we.demo03.EnableConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableConfiguration
@SpringBootApplication
public class SpringBootAutoconfigerStudyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootAutoconfigerStudyApplication.class, args);
        System.out.println(context.getBean(WeSqlSesssionFactory.class));
    }

}
