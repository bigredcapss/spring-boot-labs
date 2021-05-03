package com.we.auth.server.config;

import com.we.auth.server.interceptor.DataBaseInterceptor;
import com.we.auth.server.interceptor.JwtTokenInterceptor;
import com.we.auth.server.interceptor.JwtTokenRedisInterceptor;
import com.we.auth.server.interceptor.RedisInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc拦截器
 * @author we
 * @date 2021-05-02 11:04
 **/
@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //token+数据库
        final String[] inPatterns=new String[]{"/database/token/auth","/database/token/password/update"
                ,"/database/token/logout","/database/token/user/save"};
        // .excludePathPatterns("不需要拦截的路径")
        registry.addInterceptor(dataBaseInterceptor())
                .addPathPatterns(inPatterns);


        //token+Redis
        /*final String[] inPatterns=new String[]{"/redis/token/auth","/redis/token/password/update","/redis/token/logout"};
        registry.addInterceptor(redisInterceptor())
                .addPathPatterns(inPatterns);*/

        //jwt
        /*final String[] inPatterns=new String[]{"/jwt/token/auth","/jwt/token/password/update","/jwt/token/logout"};
        registry.addInterceptor(jwtTokenInterceptor())
                .addPathPatterns(inPatterns);*/

        //jwt+redis
        /*final String[] inPatterns = new String[]{"/jwt/redis/token/auth", "/jwt/redis/token/password/update", "/jwt/redis/token/logout"};
        registry.addInterceptor(jwtTokenRedisInterceptor())
                .addPathPatterns(inPatterns);*/

    }

    /**
     * 访问静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 跨域设置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedMethods("*");
    }

    @Bean
    public DataBaseInterceptor dataBaseInterceptor() {
        return new DataBaseInterceptor();
    }

    @Bean
    public RedisInterceptor redisInterceptor() {
        return new RedisInterceptor();
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }

    @Bean
    public JwtTokenRedisInterceptor jwtTokenRedisInterceptor() {
        return new JwtTokenRedisInterceptor();
    }


}
