package com.we.auth.server.shiro;

import com.we.auth.model.entity.User;
import com.we.auth.model.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义用户的认证、授权realm
 * @author we
 * @date 2021-05-03 11:34
 **/
@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger log= LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 资源-权限分配、授权用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 用户身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        final String userName=token.getUsername();
        final String password=String.valueOf(token.getPassword());

        log.info("--shiro 用户身份认证，当前用户名={} 密码={} --",userName,password);

        User user=userMapper.selectByUserName(userName);
        if (user==null){
            throw new UnknownAccountException("当前用户不存在！");
        }
        if (!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("用户名密码不匹配！");
        }

        //验证成功
        user.setPassword(null);
        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
