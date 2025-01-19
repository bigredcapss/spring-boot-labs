package com.we.controller;

import com.we.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author we
 * @date 2021-04-29 17:19
 **/
@RestController
public class UserController {
    @Value("${user.username}")
    private String userName;
    @Value("${user.age}")
    private Integer age;
    @Value("${user.address}")
    private String address;

    @Autowired
    private User user;


    @GetMapping("/hello")
    public String hello(){
        return "Hello SpringBoot ..." + userName + "  " + age + "  " + address  + "--->" + user;
    }
}
