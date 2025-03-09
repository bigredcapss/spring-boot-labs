package com.we.auth.server.config;

import com.we.auth.server.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 显示自定义注入配置shiro的相关组件（注：单体场景）
 * @author we
 * @date 2021-05-03 11:32
 **/
@Configuration
public class ShiroConfig {

    /**
     * securityManager-管理subject
     * @param userRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     * 过滤链配置
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //用户访问没有经过授权的接口或者url时跳转的链接
        //shiroFilter.setUnauthorizedUrl("");

        //设定用户没有经过登录认证时的跳转链接
        shiroFilter.setLoginUrl("/error/unauth");

        Map<String, String> filterChainDefinitionMap=new HashMap<>();

        filterChainDefinitionMap.put("/shiro/login","anon");
        filterChainDefinitionMap.put("/shiro/unauth","anon");
        filterChainDefinitionMap.put("/shiro/logout","anon");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }

    /**
     * shiro bean生命周期的管理
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
