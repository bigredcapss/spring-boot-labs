package com.we.auth.server.controller;

import com.google.common.collect.Lists;
import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author we
 * @date 2021-05-02 11:34
 **/
@RestController
@RequestMapping("base")
public class BaseController extends AbstractController{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试是否能向Redis设置值，能否从Redis获取值
     * @return
     */
    @RequestMapping("redis/info")
    public BaseResponse redisInfo(){
        log.info("--请求响应redis数据列表--");

        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String key="SpringBootUserAuthKey";
            String value="我的架构师之路";
            // 放入Redis中
            stringRedisTemplate.opsForValue().set(key,value);
            // 从Redis中获取值
            response.setData(stringRedisTemplate.opsForValue().get(key));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @RequestMapping("info")
    public BaseResponse info(){
        log.info("--请求响应数据列表--");
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String strOne="SpringBoot前后端分离开发之用户身份认证Demo(后端实现)";
            String strTwo="https://www.yuque.com/bigredcaps";
            String strThree="我的架构师之路";

            List<String> list= Lists.newLinkedList();
            list.add(strOne);
            list.add(strTwo);
            list.add(strThree);

            response.setData(list);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }



}
