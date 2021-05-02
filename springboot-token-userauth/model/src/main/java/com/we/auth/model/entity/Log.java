package com.we.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author we
 * @date 2021-05-02 10:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Integer id;

    private Integer userId;

    private String userName;

    private Date createTime;

    private String memo;

}
