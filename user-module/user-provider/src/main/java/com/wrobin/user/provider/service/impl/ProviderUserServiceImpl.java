package com.wrobin.user.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.po.User;
import com.wrobin.user.provider.service.ProviderUserService;
import com.wrobin.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * created by robin.wu on 2018/3/19
 **/
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Slf4j
public class ProviderUserServiceImpl implements ProviderUserService {
    @Autowired
    private UserService userService;

    @Override
    public RespDTO<User> login(LoginParam loginParam) {
        if(loginParam == null || !loginParam.isValid()){
            return RespDTO.fail("参数不合法");
        }

        Optional<User> optional = userService.login(loginParam);
        return optional.map(RespDTO::ok).orElse(RespDTO.fail("登录失败"));
    }

}
