package com.we.mapper;

import com.we.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author we
 * @date 2021-07-16 17:38
 **/
@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> list();

    @Insert("insert into user (id, username, password, enable) values (#{id}, #{username}, #{password}, #{enable})")
    int insert(User user);

}
