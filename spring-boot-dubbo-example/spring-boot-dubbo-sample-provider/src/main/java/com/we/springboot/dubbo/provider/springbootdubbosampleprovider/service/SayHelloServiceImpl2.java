package com.we.springboot.dubbo.provider.springbootdubbosampleprovider.service;

import com.we.springboot.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author we
 * @date 2021-09-06 15:00
 **/
@DubboService(registry = {"shanghai","jiaxing"},version = "2.0")
public class SayHelloServiceImpl2 implements ISayHelloService {
    @Override
    public String sayHello(String msg) {
        return "[Version2.0]Hello,"+msg+"lancoo.cn";
    }
}
