package com.we.auth.model.mapper;

import com.we.auth.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author we
 * @date 2021-05-02 10:30
 **/
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(@Param("userName") String userName);

    User selectByUserId(@Param("userId") Integer userId);

    int updatePassword(@Param("userName") String userName,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);
}
