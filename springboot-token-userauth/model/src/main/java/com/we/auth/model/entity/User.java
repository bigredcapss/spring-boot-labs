package com.we.auth.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @author we
 * @date 2021-05-02 10:26
 **/
@Data
public class User implements Serializable {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    private Byte isActive=1;

    private Date createTime;

    private Date updateTime;

}
