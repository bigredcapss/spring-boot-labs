package com.we.auth.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.auth.model.entity.User;
import com.we.auth.model.mapper.AuthTokenMapper;
import com.we.auth.model.mapper.UserMapper;
import com.we.auth.server.dto.UpdatePsdDto;
import com.we.auth.server.enums.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * spring session~service
 * @author we
 * @date 2021-05-03 11:38
 **/
@Service
public class SpringSessionService {

    private static final Logger log= LoggerFactory.getLogger(SpringSessionService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthTokenMapper authTokenMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonService commonService;


    /**
     * 登录认证 并创建session
     * @param userName
     * @param password
     * @param session
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public User authUser(String userName, String password, HttpSession session) throws Exception{
        User user=userService.authUser(userName,password);
        if (user!=null){
            user.setPassword(null);

            //需要创建session,并搭建与用户的跟踪会话
            session.setAttribute(session.getId(),user);

            //还需要为当前成功建立的session设置过期失效时间
            //超时时间：指的是在 不进行任何操作 的情况下的超时时间（即如果是处于操作期间的话，则将自动延长超时时间）
            session.setMaxInactiveInterval(Constant.SESSION_EXPIRE);
            return user;
        }
        return null;
    }

    //验证身份 ~ 直接在拦截里面写了
    /*public Boolean validateUser(HttpSession session){
        Boolean res=false;
        try {
            if (session.getAttribute(session.getId())!=null){
                res=true;
            }
        }catch (Exception e){
            log.error("spring session~验证用户身份session发生异常：",e.fillInStackTrace());
        }
        return res;
    }*/


    /**
     * 修改密码
     * @param dto
     * @param session
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final UpdatePsdDto dto, HttpSession session)throws Exception{
        if (session!=null && session.getAttribute(session.getId())!=null){
            User currUser= (User) session.getAttribute(session.getId());

            //核心业务逻辑：修改密码
            User user=userMapper.selectByUserName(currUser.getUserName());
            if (user==null){
                throw new RuntimeException("当前用户不存在，修改密码失败！");
            }
            if (!user.getPassword().equals(dto.getOldPassword())){
                throw new RuntimeException("旧密码不匹配！");
            }
            //修改密码
            int res=userMapper.updatePassword(currUser.getUserName(),dto.getOldPassword(),dto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败~请重新尝试或者联系管理员！");
            }

            this.invalidateSession(session);
        }
    }

    /**
     * 失效session
     * @param session
     * @throws Exception
     */
    public void invalidateSession(HttpSession session) throws Exception{
        String sessionId=session.getId();
        if (session.getAttribute(sessionId)!=null){
            session.removeAttribute(sessionId);
        }
    }

}
