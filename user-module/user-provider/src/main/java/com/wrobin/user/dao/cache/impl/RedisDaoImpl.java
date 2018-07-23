package com.wrobin.user.dao.cache.impl;

import com.wrobin.user.dao.cache.UserCacheDao;
import com.wrobin.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * redis缓存
 * created by robin.wu on 2018/7/5
 **/
@Slf4j
@Repository("userRedisDao")
public class RedisDaoImpl implements UserCacheDao {

    @Override
    public void put(String key, Object value) {

    }

    @Override
    public User get(String key) {
        return null;
    }

    @Override
    public void evict(String key) {

    }
}
