package com.we;

import redis.clients.jedis.Jedis;

/**
 * 基本使用
 * @author we
 * @date 2021-04-30 09:56
 **/
public class BasicTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.109",6379);
        jedis.auth("123456");
        jedis.set("we","0001");

        System.out.println(jedis.get("we"));
        jedis.close();
    }

}
