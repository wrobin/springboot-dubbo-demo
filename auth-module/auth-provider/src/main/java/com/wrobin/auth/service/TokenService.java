package com.wrobin.auth.service;

import com.wrobin.auth.po.Token;
import com.wrobin.parent.service.BaseService;

import java.util.Optional;

/**
 * created by robin.wu on 2018/3/15
 **/
public interface TokenService extends BaseService<Token> {

    Optional<Token> getTokenByAccessToken(String accessToken);

    Optional<Token> getTokenByUserId(Long userId);

    Optional<Token> refreshToken(Token token);

    Optional<Token> getTokenSelective(Token tokenParam);

}
