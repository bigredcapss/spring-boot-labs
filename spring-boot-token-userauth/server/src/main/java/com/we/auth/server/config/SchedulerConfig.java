package com.we.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 定时任务配置-线程池的方式配置
 * @author we
 * @date 2021-05-02 11:31
 **/
@Configuration
public class SchedulerConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(4);
        //最大核心线程数
        executor.setMaxPoolSize(10);
        //设置队列中等待被调度处理的任务的数量
        executor.setQueueCapacity(8);
        executor.initialize();
        return executor;
    }

}
