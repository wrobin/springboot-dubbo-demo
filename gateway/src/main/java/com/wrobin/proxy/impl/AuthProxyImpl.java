package com.wrobin.proxy.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wrobin.auth.po.Token;
import com.wrobin.auth.provider.service.ProviderTokenService;
import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.common.enums.RespStatus;
import com.wrobin.common.exceptions.AuthorizationException;
import com.wrobin.common.exceptions.CommonException;
import com.wrobin.proxy.AuthProxy;
import org.springframework.stereotype.Service;

/**
 * created by robin.wu on 2018/4/20
 **/
@Service
public class AuthProxyImpl implements AuthProxy {
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            timeout = 10000,
            check = false)
    private ProviderTokenService providerTokenService;

    public Token refreshToken(String accessToken, String refreshToken) {
        RespDTO<Token> respDTO = providerTokenService.refreshToken(accessToken,refreshToken);
        if(respDTO.isSuccess()) {
            return respDTO.getData();
        }
        throw new CommonException(respDTO.getError());
    }

    public Token login(String username, String password, String systemCode) {
        RespDTO<Token> respDTO = providerTokenService.login(username,password,systemCode);
        if(respDTO.isSuccess()) {
            return respDTO.getData();
        }
        throw new CommonException(respDTO.getError());
    }

    @Override
    public boolean verifyToken(String accessToken) {
        RespDTO<Token> respDTO = providerTokenService.getTokenByAccessToken(accessToken);
        if(respDTO.isSuccess()) {
            Token tokenResult = respDTO.getData();
            if(tokenResult == null) {
                throw new AuthorizationException(RespStatus.LOGOUT.getCode(), "Access token did not found");
            }
            if(tokenResult.isExpire()) {
                throw new AuthorizationException(RespStatus.TOKEN_EXPIRED.getCode(), "Access token validation does not pass");
            }
            return true;
        }

        return false;
    }

}
