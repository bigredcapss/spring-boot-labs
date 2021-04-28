package com.we.service;

import com.we.entity.User;

/**
 * @author we
 * @date 2021-04-27 18:33
 **/
public interface IUserService {

    User findByUsername(String userName);

    User findUserById(Integer userId);

}
