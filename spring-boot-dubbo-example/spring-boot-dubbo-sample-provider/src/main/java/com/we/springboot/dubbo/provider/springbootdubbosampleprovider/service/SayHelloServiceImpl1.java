package com.we.springboot.dubbo.provider.springbootdubbosampleprovider.service;

import com.we.springboot.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author we
 * @date 2021-09-06 14:57
 **/
@DubboService(registry = {"shanghai","jiaxing"},version = "1.0")
public class SayHelloServiceImpl1 implements ISayHelloService {
    @Override
    public String sayHello(String msg) {
        return "[Version1.0]Hello,"+msg+"lancoo.cn";
    }
}
