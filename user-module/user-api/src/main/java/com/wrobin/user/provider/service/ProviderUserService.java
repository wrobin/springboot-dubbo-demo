package com.wrobin.user.provider.service;

import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.po.User;

/**
 * created by robin.wu on 2018/3/19
 **/
public interface ProviderUserService {

    RespDTO<User> login(LoginParam loginParam);

}
