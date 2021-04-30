package com.we.datatype;

import com.we.util.JedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author we
 * @date 2021-04-30 10:24
 **/
public class HashTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().hset("classmate", "name", "guozhuren");
        JedisUtil.getJedisUtil().hset("classmate","age","18");
        JedisUtil.getJedisUtil().hset("classmate","sex","male");

        String name = JedisUtil.getJedisUtil().hget("classmate", "name");
        System.out.println(name);

        List<String> list = new ArrayList<>();
        list = JedisUtil.getJedisUtil().hmget("classmate", "name", "age", "sex");
        System.out.println(list);

    }
}
