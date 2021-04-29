package com.we;

import com.we.annotation.UserService;
import com.we.filter.FirstFilter;
import com.we.listener.FirstListener;
import com.we.servlet.FirstServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 在SpringBoot启动的时候会扫描@WebServlet注解
 */
@SpringBootApplication
@ServletComponentScan()
public class SpringbootBaseApplication {

    public static void main(String[] args) {
        /**
         * SpringBoot启动方式之一
         */
        // SpringApplication.run(SpringbootBaseApplication.class, args);

        /**
         * SpringBoot启动方式之一
         */
        /*SpringApplication springApplication = new SpringApplication(SpringbootBaseApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);*/

        /**
         * IoC容器的初始化
         */
        ApplicationContext ac = SpringApplication.run(SpringbootBaseApplication.class, args);
        System.out.println(ac.getBean(UserService.class));

    }

    @Bean
    public ServletRegistrationBean getRegistrationBean(){
        // 将要添加的Servlet封装为一个ServletRegistrationBean对象
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new FirstServlet());
        // 设置映射信息
        registrationBean.addUrlMappings("/second");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean getRegistractionBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new FirstFilter());
        bean.addUrlPatterns("/seconds");
        return bean;
    }
    @Bean
    public ServletListenerRegistrationBean getListenerRegistrationBean(){
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean(new FirstListener());
        return bean;
    }

}
