package com.we.datatype;

import com.we.util.JedisUtil;

/**
 * 字符串类型测试
 * @author we
 * @date 2021-04-30 09:21
 **/
public class StringTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().set("jiaqi","0002");
        JedisUtil.getJedisUtil().incr("jiaqi");
        String jq = JedisUtil.getJedisUtil().get("jiaqi");
        System.out.println(jq);
    }
}
