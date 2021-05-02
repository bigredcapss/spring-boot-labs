package com.we.auth.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.auth.api.enums.StatusCode;
import com.we.auth.api.response.BaseResponse;
import com.we.auth.model.entity.AuthToken;
import com.we.auth.model.entity.AuthTokenModel;
import com.we.auth.model.entity.Log;
import com.we.auth.model.entity.User;
import com.we.auth.model.mapper.AuthTokenMapper;
import com.we.auth.model.mapper.UserMapper;
import com.we.auth.server.dto.AccessTokenDto;
import com.we.auth.server.dto.UpdatePsdDto;
import com.we.auth.server.enums.Constant;
import com.we.auth.server.utils.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

/**
 * token+数据库~service
 * @author we
 * @date 2021-05-02 11:19
 **/
@Service
public class DataBaseService {

    private static final Logger log= LoggerFactory.getLogger(DataBaseService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthTokenMapper authTokenMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonService commonService;


    /**
     * 登录认证并创建token
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public AuthTokenModel authAndCreateToken(String userName, String password) throws Exception{
        User user=userService.authUser(userName,password);
        if (user!=null){
            //失效以前那些仍然在使用的access token
            authTokenMapper.invalidateTokenByUser(user.getId());
            //创建token
            Long timestamp=System.currentTimeMillis();
            AccessTokenDto tokenDto=new AccessTokenDto(user.getId(),userName,timestamp,Constant.snowFlake.nextId().toString(),
                    Constant.ACCESS_TOKEN_EXPIRE);
            String jsonStr=objectMapper.writeValueAsString(tokenDto);
            log.info("----json格式的access token字符串：{}",jsonStr);
            //AES加密
            String accessToken=EncryptUtil.aesEncrypt(jsonStr,Constant.TOKEN_AUTH_KEY);
            //token存储
            AuthToken authToken=new AuthToken();
            authToken.setUserId(user.getId());
            authToken.setAccessToken(accessToken);
            authToken.setAccessExpire(Constant.ACCESS_TOKEN_EXPIRE);
            authToken.setTokenTimestamp(timestamp);
            authToken.setCreateTime(DateTime.now().toDate());
            authTokenMapper.insertSelective(authToken);
            log.info("--token+数据库用户认证成功，成功生成accessToken--");
            AuthTokenModel tokenModel=new AuthTokenModel(accessToken,Constant.ACCESS_TOKEN_EXPIRE);
            return tokenModel;
        }
        return null;
    }

    /**
     * 验证-解析token
     * @param accessToken
     * @return
     */
    public BaseResponse validateToken(final String accessToken){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if (StringUtils.isBlank(accessToken)){
                return new BaseResponse(StatusCode.AccessTokenNotBlank);
            }

            //验证这个token是否存在
            AuthToken authToken=authTokenMapper.selectByAccessToken(accessToken);
            if (authToken==null){
                return new BaseResponse(StatusCode.AccessTokenNotExist);
            }

            // 这里这样写，防止直接修改数据库中auth_token表的access_expire和时间戳
            AccessTokenDto dto;
            //为了防止token的伪造，会主动额外做异步操作-解析token
            try {
                dto=parseAccessToken(accessToken);
            }catch (Exception e){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }
            if (dto!=null){
                //验证token是否过期
                if (System.currentTimeMillis() - dto.getTimestamp() > dto.getExpire()){
                    this.invalidateByAccessToken(accessToken);
                    return new BaseResponse(StatusCode.TokenValidateExpireToken);
                }
            }


        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 失效access token
     * @param accessToken
     */
    public void invalidateByAccessToken(final String accessToken){
        if (StringUtils.isNotBlank(accessToken)){
            authTokenMapper.invalidateByToken(accessToken);
        }
    }

    /**
     * 解密accessToken
     * @param accessToken
     * @return
     * @throws Exception
     */
    public AccessTokenDto parseAccessToken(final String accessToken) throws Exception{
        String jsonStr= EncryptUtil.aesDecrypt(accessToken, Constant.TOKEN_AUTH_KEY);
        return objectMapper.readValue(jsonStr,AccessTokenDto.class);
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
            AccessTokenDto tokenDto=parseAccessToken(accessToken);

            //修改密码
            int res=userMapper.updatePassword(tokenDto.getUserName(),dto.getOldPassword(),dto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败~旧密码不正确。。。！");
            }

            //失效掉以前有效的token
            authTokenMapper.invalidateTokenByUser(tokenDto.getUserId());
        }
    }

    /**
     * 实际业务模块操作-新增用户
     * @param accessToken
     * @param user
     * @throws Exception
     */
    public void saveUser(final String accessToken,User user) throws Exception{
        //解析token 获取用户信息
        AccessTokenDto tokenDto=this.parseAccessToken(accessToken);

        //新增用户
        user.setCreateTime(DateTime.now().toDate());
        userMapper.insertSelective(user);

        //记录日志
        Log entity=new Log(null,tokenDto.getUserId(),tokenDto.getUserName(),new Date(),"新增用户");
        commonService.insertLog(entity);
    }

}

