package com.we.auth.server.enums;

import com.we.auth.server.utils.SnowFlake;

/**
 * @author we
 * @date 2021-05-02 11:06
 **/
public class Constant {
    //token
    public static final int RESCODE_REFTOKEN_MSG = 1006;		//刷新TOKEN(有返回数据)
    public static final int RESCODE_REFTOKEN = 1007;			//刷新TOKEN

    public static final int JWT_ERRCODE_NULL = 4000;			//Token不存在
    public static final int JWT_ERRCODE_EXPIRE = 4001;			//Token过期
    public static final int JWT_ERRCODE_FAIL = 4002;			//验证不通过

    //密匙
    public static final String JWT_SECRET = "8677df7fc3a34e26a61c034d5ec8245d";
    //token有效时间
    public static final long JWT_TTL = 3600 * 1000;

    public static final String TOKEN_ISSUER="程序员实战基地";

    //token失效的时间：ms为单位
    public static final Long ACCESS_TOKEN_EXPIRE=60000L;
    public static final Long REFRESH_TOKEN_EXPIRE=60000L;
    //session失效的时间：s为单位
    public static final Integer SESSION_EXPIRE=30;

    public static final SnowFlake snowFlake=new SnowFlake(3,2);

    public static final String TOKEN_AUTH_KEY="e2bd6cee47e0402db80862a09ff4dfd6";

    public static final String TOKEN_REDIS_KEY_PREFIX="UserAuth:Key:";

    public static final String JWT_TOKEN_REDIS_KEY_PREFIX="UserAuth:JWT:Key:";
}
