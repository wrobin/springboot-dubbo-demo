package com.wrobin.auth.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wrobin.auth.po.Token;
import com.wrobin.auth.provider.service.ProviderTokenService;
import com.wrobin.auth.proxy.UserProxy;
import com.wrobin.auth.service.TokenService;
import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.common.util.DateUtil;
import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

/**
 * created by robin.wu on 2018/3/16
 **/
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        retries = 0
)
@Slf4j
public class ProviderTokenServiceImpl implements ProviderTokenService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserProxy userProxy;

    /**
     * 刷新token
     * @param accessToken
     * @param refreshToken
     * @return
     */
    public RespDTO<Token> refreshToken(String accessToken, String refreshToken) {
        log.info("ProviderTokenServiceImpl=>refreshToken=>accessToken:{},refreshToken:{}",accessToken,refreshToken);
        if(StringUtils.isBlank(accessToken)) {
            return RespDTO.fail("accessToken参数不能为空");
        }
        if(StringUtils.isBlank(refreshToken)) {
            return RespDTO.fail("refreshToken参数不能为空");
        }

        Token token = tokenService.getTokenByAccessToken(accessToken).get();
        if(token == null || token.getRefreshToken().equals(refreshToken) == false) {
            return RespDTO.fail("刷新token失败");
        }

        if(token.getRefreshTime() !=null) {
            long mis= 0;
            try {
                mis = DateUtil.dateDiff_mis(DateUtil.format(token.getRefreshTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
                if (mis>=30) {
                    token = tokenService.refreshToken(token).get();
                }
            } catch (Exception e) {
                log.error("ProviderTokenServiceImpl=>refreshToken=>e:{}",e);
            }
        }else {
            token = tokenService.refreshToken(token).get();
        }

        return RespDTO.result(token,"刷新token失败");
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public RespDTO<Token> login(String username, String password, String systemCode) {
        if(StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(systemCode)) {
            return RespDTO.fail("参数不合法");
        }
        //用户登录
        LoginParam loginParam = new LoginParam(username,password,systemCode);
        User user = userProxy.login(loginParam);
        Optional<Token> optionalT = tokenService.getTokenByUserId(user.getId());

        //没有token时创建token
        Token tokenResult = optionalT.orElseGet(()->{
            Token tokenInsert = new Token(user.getId(),systemCode);
            Optional<Token> optionalTmp = tokenService.save(tokenInsert);
            return optionalTmp.get();
        });

        return RespDTO.ok(tokenResult);
    }

    /**
     *
     * @param accessToken
     * @return
     */
    public RespDTO<Token> getTokenByAccessToken(String accessToken) {
        if(StringUtils.isBlank(accessToken)) {
            return RespDTO.fail("accessToken不能为空");
        }
        Optional<Token> optionalT = tokenService.getTokenByAccessToken(accessToken);
        return optionalT.map(RespDTO::ok).orElse(RespDTO.fail("获取token失败"));
    }
}
