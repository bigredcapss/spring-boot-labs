package com.we.auth.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author we
 * @date 2021-05-02 10:28
 **/
@Data
public class AuthToken implements Serializable {
    private Integer id;

    private Integer userId;

    private String accessToken;

    private Long accessExpire;

    private Long tokenTimestamp;

    private Byte isActive=1;

    private Date createTime;

    private Date updateTime;

}
