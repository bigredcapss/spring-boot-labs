package com.we.manager.controller;

import com.we.manager.api.CommonResult;
import com.we.manager.entity.User;
import com.we.manager.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author we
 * @date 2021-04-27 18:38
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/get")
    public CommonResult getUser(@RequestParam("userName")String userName){
        User user = userService.findByUsername(userName);
        if(user!=null){
            LOGGER.info("查找用户成功!");
            return CommonResult.success(user);
        }else {
            LOGGER.info("查找用户失败!");
            return CommonResult.failed();
        }
    }
}
