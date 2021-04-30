package com.we.util;

import java.util.ResourceBundle;

/**
 * 配置文件读取工具类
 * @author we
 * @date 2021-04-30 09:22
 **/
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("redis");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
