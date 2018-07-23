package com.wrobin.user.service.impl;

import com.wrobin.parent.service.impl.AbstractBaseService;
import com.wrobin.user.bo.LoginParam;
import com.wrobin.user.dao.mapper.UserMapper;
import com.wrobin.user.po.User;
import com.wrobin.user.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Optional;

/**
 * created by robin.wu on 2018/5/9
 **/
@Service
public class UserServiceImpl extends AbstractBaseService<User> implements UserService {
    private static final String MD5_SALT = "ABC123";
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> login(LoginParam loginParam) {
        Example example = new Example(User.class,false);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mobileNo",loginParam.getUsername());
        criteria.andEqualTo("systemCode",loginParam.getSystemCode());

        String md5Pass = DigestUtils.md5Hex((loginParam.getUsername() + loginParam.getPassword() + MD5_SALT).getBytes());
        criteria.andEqualTo("password",md5Pass);

        return Optional.ofNullable(userMapper.selectOneByExample(example));
    }
}
