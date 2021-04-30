package com.we.datatype;

import com.we.util.JedisUtil;

import java.util.Set;

/**
 * @author we
 * @date 2021-04-30 10:47
 **/
public class ZsetTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().zadd("myzset",90,"java");
        JedisUtil.getJedisUtil().zadd("myzset",80,"python");
        JedisUtil.getJedisUtil().zadd("myzset",70,"go");
        Set<String> set = JedisUtil.getJedisUtil().zrangeByScore("myzset", 100, 10);
        System.out.println(set);

    }
}
