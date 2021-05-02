package com.we.auth.server.interceptor;

import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import com.we.auth.server.service.CommonService;
import com.we.auth.server.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * redis+token拦截器
 * @author we
 * @date 2021-05-02 11:09
 **/
@Component
public class RedisInterceptor implements HandlerInterceptor {

    private static final Logger log= LoggerFactory.getLogger(RedisInterceptor.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private RedisService redisService;


    //获取前端塞在请求头header里面的accessToken字段的值，然后进行验证与解析
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            String accessToken=request.getHeader("accessToken");
            if (StringUtils.isBlank(accessToken)){
                log.error("--redis+token用户验证失败--");

                BaseResponse baseResponse=new BaseResponse(StatusCode.Fail.getCode(),"accessToken必填~请先进行登录,并将生成的accessToken塞入http请求头的accessToken字段中");
                commonService.print(response,baseResponse);
            }else{
                log.info("--redis+token用户验证-开始进行--");

                BaseResponse result=redisService.validateToken(accessToken);
                if (Objects.equals(result.getCode(),StatusCode.Success.getCode())){
                    return true;
                }else{
                    commonService.print(response,result);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (response.getStatus()==500){
            modelAndView.setViewName("/error/500");
        }else if (response.getStatus()==404){
            modelAndView.setViewName("/error/404");
        }
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
