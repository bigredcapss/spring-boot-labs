package com.we.auth.server.controller;

import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author we
 * @date 2021-05-02 11:37
 **/
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping(value = "500",method = RequestMethod.GET)
    public String error500(){
        return "500";
    }

    @RequestMapping(value = "404",method = RequestMethod.GET)
    public String error404(){
        return "404";
    }

    @RequestMapping("unauth")
    @ResponseBody
    public BaseResponse unauth(){
        return new BaseResponse(StatusCode.AccessSessionNotExist);
    }
}