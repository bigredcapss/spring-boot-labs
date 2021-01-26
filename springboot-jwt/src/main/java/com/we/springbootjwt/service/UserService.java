package com.we.springbootjwt.service;

import com.we.springbootjwt.entity.User;
import com.we.springbootjwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author BigRedCaps
 * @date 2021/1/25 22:50
 */
@Service("UserService")
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User findByUsername(User user){
        return userMapper.findByUsername(user.getUsername());
    }
    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

}
