package com.we.pipeline;

import redis.clients.jedis.Jedis;

/**
 * 使用Pipeline前的使用Jedis发送命令，Redis服务端接收处理，返回
 * @author we
 * @date 2021-04-30 11:40
 **/
public class MultiTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.69", 6379);
        jedis.auth("123456");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set("key"+i,i+"");
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end - start) +"毫秒");
        jedis.close();
    }
}
