package com.wrobin.auth.proxy.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wrobin.auth.proxy.UserProxy;
import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.common.exceptions.CommonException;
import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.po.User;
import com.wrobin.user.provider.service.ProviderUserService;
import org.springframework.stereotype.Service;

/**
 * created by robin.wu on 2018/3/19
 **/
@Service
public class UserProxyImpl implements UserProxy {
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            timeout = 10000,
            check = false)
    private ProviderUserService providerUserService;

    @Override
    public User login(LoginParam loginParam) {
        RespDTO<User> respDTO = providerUserService.login(loginParam);
        if(respDTO.isSuccess()) {
            return respDTO.getData();
        }
        throw new CommonException(respDTO.getError());
    }
}
