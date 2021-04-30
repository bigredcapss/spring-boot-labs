package com.we.pubsub;

import redis.clients.jedis.Jedis;

/**
 * @author we
 * @date 2021-04-30 14:02
 **/
public class PublishTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.69", 6379);
        jedis.publish("we-123","666");
        jedis.publish("we-abc","你好帅!");


    }
}
