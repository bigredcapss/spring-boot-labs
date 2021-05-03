package com.we.auth.server.controller;

import com.google.common.collect.Maps;
import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import com.we.auth.server.dto.UpdatePsdDto;
import com.we.auth.server.service.ShiroService;
import com.we.auth.server.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * shiro session 认证模式
 * @author we
 * @date 2021-05-03 11:39
 **/
@RestController
@RequestMapping("shiro")
public class ShiroController extends AbstractController{

    @Autowired
    private ShiroService shiroService;


    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String userName, @RequestParam String password){
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //交由shiro的组件/api进行实现
            Subject subject=SecurityUtils.getSubject();
            //判断是否登录认证过
            if (!subject.isAuthenticated()){
                UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
                subject.login(token);
            }

            if (subject.isAuthenticated()){
                response.setData(SecurityUtils.getSubject().getPrincipal());
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }

        return response;
    }

    /**
     * 访问需要被授权的资源
     * @return
     */
    @RequestMapping(value = "auth",method = RequestMethod.GET)
    public BaseResponse tokenAuth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            String info="shiro~成功访问需要被拦截的链接/资源";
            resMap.put("info",info);
            resMap.put("currUser",SecurityUtils.getSubject().getPrincipal());

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
            String info="shiro~成功访问不需要被拦截的链接/资源";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 退出登录注销session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public BaseResponse logout(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            SecurityUtils.getSubject().logout();

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 修改密码
     * @param dto
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "password/update",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult){
        log.info("--shiro~修改密码--");

        String res= ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            shiroService.updatePassword(dto);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
