package com.we.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author we
 * @date 2021-05-01 09:46
 **/
public class PipelineGet {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                Jedis jedis = new Jedis("172.16.63.69", 6379);
                jedis.auth("123456");
                Set<String> keys = jedis.keys("batch*");
                ArrayList<String> result = new ArrayList<>();
                long t1 = System.currentTimeMillis();
                for (String key : keys) {
                    result.add(jedis.get(key));
                }
                System.out.println("直接get耗时："+(System.currentTimeMillis() - t1));
            }
        }.start();

        new Thread(){
            public void run(){
                Jedis jedis = new Jedis("172.16.63.69", 6379);
                jedis.auth("123456");
                Set<String> keys = jedis.keys("batch*");
                List<Object> result = new ArrayList<>();
                Pipeline pipelined = jedis.pipelined();
                long t1 = System.currentTimeMillis();
                for (String key : keys) {
                    pipelined.get(key);
                }
                result = pipelined.syncAndReturnAll();
                System.out.println("Pipeline get耗时："+(System.currentTimeMillis() - t1));
            }
        }.start();
    }
}
