package com.we.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author we
 * @date 2021-04-27 18:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer Id;
    String username;
    String password;
}
