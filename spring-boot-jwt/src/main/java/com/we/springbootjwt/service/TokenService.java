package com.we.springbootjwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.we.springbootjwt.entity.User;
import org.springframework.stereotype.Service;


/**
 * @author BigRedCaps
 * @date 2021/1/25 22:50
 */
@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
