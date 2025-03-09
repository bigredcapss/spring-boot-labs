package com.we.service.impl;

import com.we.entity.User;
import com.we.mapper.UserMapper;
import com.we.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author we
 * @date 2021-04-27 18:34
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }
}
