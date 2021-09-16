package com.we.es.service;

import com.we.es.entity.User;

import java.util.List;

/**
 * @author we
 * @date 2021-09-16 15:36
 **/
public interface UserService {
    /**
     * 新增用户信息
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 根据关键字进行全文检索
     * @param searchContent
     * @return
     */
    List<User> search(String searchContent);

    /**
     * 根据名字进行分页查询
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    List<User> searchByName(Integer pageNumber,Integer pageSize,String name);

    /**
     * 根据关键字进行搜索并分页
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<User> searchUser(Integer pageNumber,Integer pageSize,String searchContent);

    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<User> searchUserByWeight(String searchContent);


}
