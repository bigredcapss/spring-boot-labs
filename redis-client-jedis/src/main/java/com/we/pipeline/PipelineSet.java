package com.we.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author we
 * @date 2021-05-01 09:43
 **/
public class PipelineSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.69", 6379);
        jedis.auth("123456");
        Pipeline pipelined = jedis.pipelined();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            pipelined.set("batch"+i,""+i);
        }
        pipelined.syncAndReturnAll();
        long t2 = System.currentTimeMillis();
        System.out.println("耗时："+(t2-t1)+"ms");
    }
}
