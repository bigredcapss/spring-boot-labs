package com.we.es.controller;

import com.we.es.entity.User;
import com.we.es.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author we
 * @date 2021-09-16 16:52
 **/
@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public boolean createUser(@RequestBody User user){
        return userService.insert(user);
    }


    @GetMapping("/user/searchContent")
    public List<User> search(@RequestParam(value = "searchContent") String searchContent) {
        return userService.search(searchContent);
    }

    @GetMapping("/user")
    public List<User> searchUser(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "5",required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUser(pageNumber, pageSize, searchContent);
    }


    @GetMapping("/user2")
    public List<User> searchUserByWeight(@RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUserByWeight(searchContent);
    }
}
