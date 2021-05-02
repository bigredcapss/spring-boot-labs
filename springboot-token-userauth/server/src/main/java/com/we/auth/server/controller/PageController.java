package com.we.auth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author we
 * @date 2021-05-02 11:39
 **/
@Controller
@RequestMapping("page")
public class PageController extends AbstractController{

    @RequestMapping("info")
    public String info(ModelMap modelMap){
        log.info("--请求响应跳转页面--");

        modelMap.put("code","https://www.yuque.com/bigredcaps");
        return "page";
    }

}
