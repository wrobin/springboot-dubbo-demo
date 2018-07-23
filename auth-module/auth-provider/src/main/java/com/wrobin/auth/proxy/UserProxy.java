package com.wrobin.auth.proxy;

import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.po.User;

/**
 * created by robin.wu on 2018/3/19
 **/
public interface UserProxy {
    User login(LoginParam loginParam);
}
