package com.we.springbootautoconfigerstudy;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义weredisson-spring-boot-starter测试
 * @author we
 * @date 2021-08-25 14:41
 **/
@RestController
public class RedissonStarterController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/say")
    public String say(){
        RBucket bucket = redissonClient.getBucket("name");
        if(bucket.get()==null){
            bucket.set("bigredcaps.com");
        }
        return bucket.get().toString();
    }



}
