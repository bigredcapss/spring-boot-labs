package com.we.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author we
 * @date 2021-04-27 16:47
 **/
@Slf4j
@RestController
@RequestMapping("/logback")
public class LogbackController {

    @GetMapping("/test")
    public String logback(){
        log.info("测试{}，输出{}", "demo1", "info level log");
        return "hello";
    }






}
