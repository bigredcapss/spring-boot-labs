package com.we.demo04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author we
 * @date 2021-08-25 11:40
 **/
public class ConditionMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        System.out.println(context.getBean(DemoService.class));

    }
}
