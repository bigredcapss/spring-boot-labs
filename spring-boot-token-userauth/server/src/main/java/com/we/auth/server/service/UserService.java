package com.we.auth.server.service;

import com.we.auth.model.entity.User;
import com.we.auth.model.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author we
 * @date 2021-05-02 11:12
 **/
@Service
public class UserService {

    private static final Logger log= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户认证
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public User authUser(String userName,String password) throws Exception{
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            throw new RuntimeException("用户名或者密码不能为空！");
        }
        User user=userMapper.selectByUserName(userName);
        if (user==null){
            throw new RuntimeException("当前用户不存在！");
        }
        if (!password.equals(user.getPassword())){
            throw new RuntimeException("用户名密码不匹配！");
        }
        return user;
    }

}
