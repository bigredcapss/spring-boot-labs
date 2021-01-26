package com.we.springbootjwt.mapper;


import com.we.springbootjwt.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author BigRedCaps
 * @date 2021/1/25 22:41
 */
public interface UserMapper {
    User findByUsername (@Param("username") String username);
    User findUserById (@Param("Id") String Id);
}
