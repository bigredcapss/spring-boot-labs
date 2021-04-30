package com.we.datatype;

import com.we.util.JedisUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author we
 * @date 2021-04-30 10:56
 **/
public class BytesTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());
        File file = new File("E:/2.jpeg");
        byte[] bytes = FileUtils.readFileToByteArray(file);

        String s = new String(bytes,Charset.defaultCharset());
        System.out.println(s);

        JedisUtil.getJedisUtil().set("mybytes2",s);
        String mybytes2 = JedisUtil.getJedisUtil().get("mybytes2");
        System.out.println(mybytes2);

    }
}
