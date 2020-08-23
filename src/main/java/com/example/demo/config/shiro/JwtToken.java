package com.example.demo.config.shiro;//package com.edu.core.config.shiro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: bysun
 * @Date: 2019/5/26 10:10
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
