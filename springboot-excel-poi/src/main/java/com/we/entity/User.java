package com.we.entity;

import lombok.Data;

/**
 * @author we
 * @date 2021-07-16 17:37
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer enable;
}
