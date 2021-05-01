package com.we.monitor;

import com.google.common.util.concurrent.AtomicLongMap;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

/**
 * 使用monitor查看redis请求日志
 * @author we
 * @date 2021-04-30 11:38
 **/
public class MonitorTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.69", 6379);
        jedis.auth("123456");
        jedis.monitor(new JedisMonitor() {
            @Override
            public void onCommand(String s) {
                System.out.println("#monitor:"+ s);
                AtomicLongMap<String> ATOMIC_LONG_MAP = AtomicLongMap.create();
                // ATOMIC_LONG_MAP.incrementAndGet(command);

            }
        });
    }
}
