package com.we.auth.model.mapper;

import com.we.auth.model.entity.Log;

/**
 * @author we
 * @date 2021-05-02 10:30
 **/
public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}
