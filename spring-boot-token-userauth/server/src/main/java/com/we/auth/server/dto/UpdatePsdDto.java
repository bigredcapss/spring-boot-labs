package com.we.auth.server.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 更新密码Dto
 * @author we
 * @date 2021-05-02 11:06
 **/
@Data
public class UpdatePsdDto implements Serializable {

    @NotBlank(message = "旧密码不能为空！")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空！")
    private String newPassword;

}
