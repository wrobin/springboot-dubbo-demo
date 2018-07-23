package com.wrobin.user.service;

import com.wrobin.parent.service.BaseService;
import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.po.User;

import java.util.Optional;

/**
 * created by robin.wu on 2018/3/19
 **/
public interface UserService extends BaseService<User> {

    Optional<User> login(LoginParam loginParam);
}
