package com.we.pubsub;

import redis.clients.jedis.Jedis;

/**
 * @author we
 * @date 2021-04-30 11:40
 **/
public class ListenTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.69", 6379);
        final MyListener listener = new MyListener();
        // 使用模式匹配的方式设置频道
        // 会阻塞
        jedis.psubscribe(listener,new String[]{"we-*"});

    }

}
