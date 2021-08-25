package com.we.springbootautoconfigerstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author we
 * @date 2021-08-25 11:25
 **/
@RestController
public class HelloController {

    /**
     * 这里能够实现注入的前提是？ IOC存在实例（自动装配）
     */
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/say")
    public String say(){
        return redisTemplate.opsForValue().get("name");
    }


}
