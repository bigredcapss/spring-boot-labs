package com.we.shard;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

/**
 * Jedis实现集群的方法主要是采用一致性哈希分片（Shard），将不同的key分配到不同的redis server上，达到横向扩展的目的。
 * @author we
 * @date 2021-04-30 11:41
 **/
public class ShardingTest {
    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // Redis服务器
        JedisShardInfo shardInfo1 = new JedisShardInfo("172.16.63.69", 6379);
        JedisShardInfo shardInfo2 = new JedisShardInfo("172.16.63.109", 6379);
        shardInfo1.setPassword("123456");
        shardInfo2.setPassword("123456");

        // 连接池
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2);
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);

        ShardedJedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            for(int i=0; i<10; i++){
                jedis.set("k"+i, ""+i);
            }
            for(int i=0; i<10; i++){
                Client client = jedis.getShard("k"+i).getClient();
                System.out.println("取到值："+jedis.get("k"+i)+"，"+"当前key位于：" + client.getHost() + ":" + client.getPort());
            }
        }finally{
            if(jedis!=null) {
                jedis.close();
            }
        }
    }
}
