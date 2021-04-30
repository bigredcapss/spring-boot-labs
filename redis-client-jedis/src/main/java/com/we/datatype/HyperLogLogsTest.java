package com.we.datatype;

import redis.clients.jedis.Jedis;

/**
 * @author we
 * @date 2021-04-30 11:21
 **/
public class HyperLogLogsTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.109", 6379);
        jedis.auth("123456");

        float size = 100000;

        for (int i = 0; i < size; i++) {
            jedis.pfadd("hll","hll-"+i);
        }
        long total = jedis.pfcount("hll");
        System.out.println(String.format("统计个数：%s",total));
        System.out.println(String.format("正确率：%s",(total / size)));
        System.out.println(String.format("误差率：%s",1-(total / size)));
    }
}
