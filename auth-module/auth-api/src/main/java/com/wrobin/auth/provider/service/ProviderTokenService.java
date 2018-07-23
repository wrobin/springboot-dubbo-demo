package com.wrobin.auth.provider.service;

import com.wrobin.auth.po.Token;
import com.wrobin.common.dto.resp.RespDTO;

/**
 * created by robin.wu on 2018/3/16
 **/
public interface ProviderTokenService {
    RespDTO<Token> refreshToken(String accessToken, String refreshToken);

    RespDTO<Token> login(String username, String password, String systemCode);

    RespDTO<Token> getTokenByAccessToken(String accessToken);
}
