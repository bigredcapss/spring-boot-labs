package com.we.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author we
 * @date 2021-05-02 10:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenModel implements Serializable {

    private String accessToken;

    private Long accessExpire;

}
