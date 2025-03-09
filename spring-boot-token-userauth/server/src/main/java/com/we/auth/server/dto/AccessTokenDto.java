package com.we.auth.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * accessToken的内部字段信息
 * @author we
 * @date 2021-05-02 11:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto implements Serializable {

    private Integer userId;

    private String userName;

    private Long timestamp;

    private String randomStr;

    private Long expire;

}