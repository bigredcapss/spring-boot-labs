package com.we.auth.server.scheduler;

import com.we.auth.model.mapper.AuthTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 通用的定时任务调度
 * @author we
 * @date 2021-05-02 11:32
 **/
@Component
@EnableAsync
public class CommonScheduler {

    private static final Logger log= LoggerFactory.getLogger(CommonScheduler.class);

    @Autowired
    private AuthTokenMapper authTokenMapper;

    /**
     * 剔除掉那些已经失效的token     cron=建议一个月一次（视具体业务而定）
     * 每个月的 28 日 00:00:00 运行
     *
     * 交给线程池去调度执行
     */
    @Scheduled(cron = "0 0 0 28 * ?")
    @Async("taskExecutor")
    public void deleteInvalidateToken(){
        try {
            log.info("--剔除掉那些已经失效的token--定时任务调度开启--");
            authTokenMapper.deleteUnactiveToken();
            //实际上，不建议删除；交给运维-自动抽取出那些失效的token，进行转移 (etl)
        }catch (Exception e){
            log.error("--剔除掉那些已经失效的token--发生异常：",e.fillInStackTrace());
        }
    }


}
