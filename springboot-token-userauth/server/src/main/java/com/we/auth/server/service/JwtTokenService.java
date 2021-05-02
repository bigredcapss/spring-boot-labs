package com.we.auth.server.service;

import com.we.auth.api.response.BaseResponse;
import com.we.auth.model.entity.AuthTokenModel;
import com.we.auth.model.entity.User;
import com.we.auth.model.mapper.UserMapper;
import com.we.auth.server.dto.UpdatePsdDto;
import com.we.auth.server.enums.Constant;
import com.we.auth.server.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * jwt~service
 * @author we
 * @date 2021-05-02 11:21
 **/
@Service
public class JwtTokenService {

    private static final Logger log= LoggerFactory.getLogger(JwtTokenService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    //登录认证并创建token
    @Transactional(rollbackFor = Exception.class)
    public AuthTokenModel authAndCreateToken(String userName, String password) throws Exception{
        User user=userService.authUser(userName,password);
        if (user!=null){
            //创建token
            String accessToken= JwtUtil.createJWT(user.getId().toString(),userName, Constant.ACCESS_TOKEN_EXPIRE);

            log.info("--jwt用户认证成功，成功生成accessToken--");
            AuthTokenModel tokenModel=new AuthTokenModel(accessToken,Constant.ACCESS_TOKEN_EXPIRE);
            return tokenModel;
        }
        return null;
    }


    //jwt验证解析token
    public BaseResponse validateToken(final String accessToken){
        return JwtUtil.validateJWT(accessToken);
    }



    //修改密码
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final String accessToken, final UpdatePsdDto dto)throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //解析access token，获取用户信息
            Claims claims= JwtUtil.parseJWT(accessToken);
            if (claims==null){
                throw new RuntimeException("当前Token无效！");
            }

            //核心业务逻辑：修改密码
            User user=userMapper.selectByUserName(claims.getSubject());
            if (user==null){
                throw new RuntimeException("当前Token对应的是无效的用户！");
            }
            if (!user.getPassword().equals(dto.getOldPassword())){
                throw new RuntimeException("旧密码不匹配！");
            }
            //修改密码
            int res=userMapper.updatePassword(claims.getSubject(),dto.getOldPassword(),dto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败~请重新尝试或者联系管理员！");
            }
        }
    }

}
