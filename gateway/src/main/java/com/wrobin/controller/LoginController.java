package com.wrobin.controller;

import com.wrobin.auth.po.Token;
import com.wrobin.common.annotations.HttpAuthentication;
import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.common.enums.SystemCodes;
import com.wrobin.proxy.AuthProxy;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by robin.wu on 2018/7/23
 **/
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AuthProxy authProxy;

    @HttpAuthentication(ignore = true)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "form")}
    )
    @PostMapping("/login")
    public RespDTO<Token> login(@RequestParam(name = "username")String username,
                                @RequestParam(name = "password")String password) {
        if(StringUtils.isBlank(username)) {
            return RespDTO.fail("用户名不能为空");
        }

        if(StringUtils.isBlank(password)) {
            return RespDTO.fail("密码不能为空");
        }

        Token token = authProxy.login(username,password, SystemCodes.TEST.getCode());
        if(token != null) {
            return RespDTO.ok(token);
        }

        return RespDTO.fail("登录失败");
    }
}
