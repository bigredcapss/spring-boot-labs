package com.we.auth.server.controller;

import com.google.common.collect.Maps;
import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import com.we.auth.server.dto.UpdatePsdDto;
import com.we.auth.server.service.SpringSessionService;
import com.we.auth.server.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * spring session 认证模式
 * @author we
 * @date 2021-05-03 11:37
 **/
@RestController
@RequestMapping("spring/session")
public class SpringSessionController extends AbstractController{

    @Autowired
    private SpringSessionService springSessionService;


    /**
     * 用户登录
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String userName, @RequestParam String password, HttpSession session){
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(springSessionService.authUser(userName,password,session));

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 访问需要被授权的资源
     * @param session
     * @return
     */
    @RequestMapping(value = "auth",method = RequestMethod.GET)
    public BaseResponse tokenAuth(HttpSession session){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            String info="spring session~成功访问需要被拦截的链接/资源";
            resMap.put("info",info);
            resMap.put("currUser",session.getAttribute(session.getId()));

            response.setData(resMap);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 访问不需要被授权的资源
     * @return
     */
    @RequestMapping(value = "unauth",method = RequestMethod.GET)
    public BaseResponse tokenUnauth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="spring session~成功访问不需要被拦截的链接/资源";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 修改密码
     * @param dto
     * @param bindingResult
     * @param session
     * @return
     */
    @RequestMapping(value = "password/update",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult, HttpSession session){
        log.info("--spring session~修改密码--");

        String res= ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            springSessionService.updatePassword(dto,session);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 退出注销登录~前端需要清除Cookie并重新进行登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public BaseResponse logout(HttpSession session){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            springSessionService.invalidateSession(session);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


}
