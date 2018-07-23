package com.wrobin.proxy;

import com.wrobin.auth.po.Token;

/**
 * created by robin.wu on 2018/3/16
 **/
public interface AuthProxy {
    Token refreshToken(String accessToken, String refreshToken);

    Token login(String username, String password, String systemCode);

    boolean verifyToken(String accessToken);

}
