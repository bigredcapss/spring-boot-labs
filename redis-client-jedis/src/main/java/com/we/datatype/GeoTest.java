package com.we.datatype;

import com.we.util.JedisUtil;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * @author we
 * @date 2021-04-30 11:10
 **/
public class GeoTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.63.109", 6379);
        jedis.auth("123456");

        HashMap<String, GeoCoordinate> geoMap = new HashMap<>();
        GeoCoordinate geoCoordinate = new GeoCoordinate(120.7604277, 30.77399224);
        geoMap.put("lg",geoCoordinate);

        jedis.geoadd("positions",geoMap);
        System.out.println(jedis.geopos("positions","lg"));
        jedis.close();


    }
}
