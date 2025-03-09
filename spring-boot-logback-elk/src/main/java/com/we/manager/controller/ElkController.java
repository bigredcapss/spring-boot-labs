package com.we.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author we
 * @date 2021-04-28 09:53
 **/
@RestController
@RequestMapping("/elk")
@Slf4j
public class ElkController {

    @GetMapping("/index")
    public void index(){
        log.info("测试elk的info日志收集");
        log.debug("测试elk的debug日志收集");
        log.error("测试elk的error日志收集");
    }
}
