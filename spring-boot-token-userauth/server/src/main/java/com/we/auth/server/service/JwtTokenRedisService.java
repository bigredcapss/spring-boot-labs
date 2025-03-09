package com.we.auth.server.service;

import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import com.we.auth.model.entity.AuthTokenModel;
import com.we.auth.model.entity.User;
import com.we.auth.model.mapper.UserMapper;
import com.we.auth.server.dto.UpdatePsdDto;
import com.we.auth.server.enums.Constant;
import com.we.auth.server.utils.JwtRedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * jwt+redis~service
 * @author we
 * @date 2021-05-02 11:20
 **/
@Service
public class JwtTokenRedisService {

    private static final Logger log= LoggerFactory.getLogger(JwtTokenRedisService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 登录认证、创建token并缓存设置失效token
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public AuthTokenModel authAndCreateToken(String userName, String password) throws Exception{
        User user=userService.authUser(userName,password);
        if (user!=null){
            //创建token
            String accessToken=JwtRedisUtil.createJWT(user.getId().toString(),userName);

            //缓存token、设置过期失效时间
            ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
            valueOperations.set(Constant.JWT_TOKEN_REDIS_KEY_PREFIX+user.getId(),accessToken,Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.MILLISECONDS);

            log.info("--jwt+redis用户认证成功，成功生成accessToken--");
            AuthTokenModel tokenModel=new AuthTokenModel(accessToken,Constant.ACCESS_TOKEN_EXPIRE);
            return tokenModel;
        }
        return null;
    }


    /**
     * jwt验证解析token
     * @param accessToken
     * @return
     */
    public BaseResponse validateToken(final String accessToken){
        try {
            //验证token的合法性全部交给jwt
            Claims claims=JwtRedisUtil.validateJWT(accessToken);
            if (claims==null || StringUtils.isBlank(claims.getId())){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }

            //失效过期等判断交给缓存中间件redis
            final String key=Constant.JWT_TOKEN_REDIS_KEY_PREFIX+claims.getId();
            if (!stringRedisTemplate.hasKey(key)){
                return new BaseResponse(StatusCode.AccessTokenNotExistRedis);
            }

            //最后是校验一下当前过来的Token是否是之前采用加密算法生成的Token并存于缓存中
            ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
            String redisToken=valueOperations.get(key);
            if (!accessToken.equals(redisToken)){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }

            //执行到下面这一步时，即代表验证成功
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }

    }


    /**
     * 修改密码
     * @param accessToken
     * @param dto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final String accessToken, final UpdatePsdDto dto)throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //解析access token，获取用户信息
            Claims claims=JwtRedisUtil.validateJWT(accessToken);

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

            //失效以前的token
            this.invalidateByAccessToken(accessToken);
        }
    }

    /**
     * 失效access token
     * @param accessToken
     * @throws Exception
     */
    public void invalidateByAccessToken(final String accessToken) throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //正确解析access token
            Claims claims= JwtRedisUtil.validateJWT(accessToken);

            final String key= Constant.JWT_TOKEN_REDIS_KEY_PREFIX+claims.getId();
            if (stringRedisTemplate.hasKey(key)){
                stringRedisTemplate.delete(key);
            }
        }
    }


}
