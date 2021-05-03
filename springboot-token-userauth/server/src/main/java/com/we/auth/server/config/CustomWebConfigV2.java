package com.we.auth.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 用于基于spring session的认证模式
 * @author we
 * @date 2021-05-03 10:51
 **/
@Configuration
public class CustomWebConfigV2 implements WebMvcConfigurer {


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        final String[] inPatterns=new String[]{"/spring/session/auth","/spring/session/password/update","/spring/session/logout"};
        registry.addInterceptor(new SpringSessionInterceptor())
                .addPathPatterns(inPatterns);

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

    /**
     * Spring Session拦截器
     */
    @Configuration
    public class SpringSessionInterceptor implements HandlerInterceptor {

        //拦截受保护的资源/链接url，进行用户身份的认证
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session=request.getSession();
            String sessionId=session.getId();
            System.out.println("---spring session当前登录用户的sessionId："+sessionId);

            if (session.getAttribute(sessionId)!=null){
                return true;
            }

            //没登录-故而不会有session
            try {
                response.setStatus(HttpStatus.OK.value());
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setHeader("Cache-Control", "no-cache, must-revalidate");
                PrintWriter writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(new BaseResponse<>(StatusCode.AccessSessionNotExist)));
                writer.flush();
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
            if (response.getStatus()==500){
                modelAndView.setViewName("/error/500");
            }else if (response.getStatus()==404){
                modelAndView.setViewName("/error/404");
            }
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        }
    }










}
