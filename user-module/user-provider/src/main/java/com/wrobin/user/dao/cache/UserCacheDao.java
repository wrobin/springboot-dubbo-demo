package com.wrobin.user.dao.cache;

import com.wrobin.user.po.User;

/**
 * created by robin.wu on 2018/5/3
 **/
public interface UserCacheDao {

    void put(String key, Object value);

    User get(String key);

    void evict(String key);

    /**其他扩展方法**/
}
