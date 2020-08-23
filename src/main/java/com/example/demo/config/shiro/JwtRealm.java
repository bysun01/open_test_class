package com.example.demo.config.shiro;

import com.example.demo.service.CacheService;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtHelper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;


@Component
public class JwtRealm extends AuthorizingRealm {

    private static final String JWT = "jwt:";
    private static final int NUM_4 = 4;
    private static final char LEFT = '{';
    private static final char RIGHT = '}';

    private UserService userService;
    private CacheService cacheService;

    @Autowired
    public void getBean(UserService userService, CacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    public Class<?> getAuthenticationTokenClass() {
        // 此realm只支持jwtToken
        return JwtToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String jwt = (String) authenticationToken.getCredentials();
        Map<String, String> map;
        String id = null;
        try {
            // 预先解析Payload
            // 没有做任何的签名校验
            Object o = cacheService.getCommonCache(jwtToken.getToken());
            if (null == o) {
                //令牌无效
                throw new AuthenticationException("token");
            }
            cacheService.refreshCache(jwtToken.getToken());
            map = JwtHelper.verifyToken(jwt);
            id = map.get("id");
        } catch (Exception e) {
            //令牌格式错误
            throw new AuthenticationException("token");
        }
        return new SimpleAuthenticationInfo(jwt, jwt, this.getName());
    }
}

