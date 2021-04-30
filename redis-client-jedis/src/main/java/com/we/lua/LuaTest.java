package com.we.lua;

import com.we.util.ResourceUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

/**
 * Reids中如何执行Lua脚本/Lua脚本如何调用Redis命令
 * 案例--用lua脚本对ip进行限流
 * @author we
 * @date 2021-04-30 11:38
 **/
public class LuaTest {
    public static void main(String[] args) {
        Jedis jedis = getJedisUtil();
        jedis.eval("return redis.call('set',KEYS[1],ARGV[1])", 1, "test:lua:key", "we0001lua");
        System.out.println(jedis.get("test:lua:key"));
        for (int i = 0; i < 10; i++) {
            limit();
        }


    }

    /**
     * 10s内限制访问5次
     */
    public static void limit(){
        Jedis jedis = getJedisUtil();
        String lua = "local num = redis.call('incr', KEYS[1])\n" +
                "if tonumber(num) == 1 then\n" +
                "\tredis.call('expire', KEYS[1], ARGV[1])\n" +
                "\treturn 1\n" +
                "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                "\treturn 0\n" +
                "else \n" +
                "\treturn 1\n" +
                "end\n";
        Object result = jedis.evalsha(jedis.scriptLoad(lua), Arrays.asList("localhost"), Arrays.asList("10", "5"));
        System.out.println(result);
    }

    /**
     * 自乘运算--lua脚本
     */
    public static void multiplication(){
        Jedis jedis = getJedisUtil();
        String lua = "local curVal = redis.call(\"get\",KEYS[1])\n" +
                "if curVal == false then\n" +
                " curVal = 0\n" +
                "else\n" +
                " curVal = tonumber(curVal)\n" +
                "end\n" +
                "curVal = curVal * tonumber(ARGV[1])\n" +
                "redis.call(\"set\",KEYS[1],curVal)\n" +
                "return curVal";
    }


    private static Jedis getJedisUtil(){
        String ip = ResourceUtil.getKey("redis.host");
        Integer port = Integer.valueOf(ResourceUtil.getKey("redis.port"));
        String password = ResourceUtil.getKey("redis.password");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool pool = null;
        if(null==password || "".equals(password)){
            pool = new JedisPool(jedisPoolConfig, ip, port, 10000);
        }else{
            pool = new JedisPool(jedisPoolConfig,ip,port,10000,password);
        }
        return pool.getResource();
    }

}
