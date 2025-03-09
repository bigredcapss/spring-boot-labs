package com.we.mapper;

import com.we.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author we
 * @date 2021-04-27 18:26
 **/
@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    User findUserById(@Param("Id") Integer Id);
}
