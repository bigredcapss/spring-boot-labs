package com.we.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author we
 * @date 2021-04-29 18:24
 **/
@EnableWeImport
public class ImportMain {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ImportMain.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames){
            System.out.println(beanName);
        }
    }
}
