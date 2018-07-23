package com.wrobin.user.dao.cache.impl;

import com.wrobin.user.dao.cache.UserCacheDao;
import com.wrobin.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Repository;

/**
 * ehcache缓存
 * created by robin.wu on 2018/5/3
 **/
@Repository("userEhcacheDao")
public class EhcacheDaoImpl implements UserCacheDao {
    private static final String CACHE_NAME = "user";

    @Autowired
    @Qualifier("cacheManager")
    private EhCacheCacheManager cacheManager;

    @Override
    public void put(String key, Object value) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        cache.put(key,value);
    }

    @Override
    public User get(String key) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        if(cache.get(key) !=null) {
            return (User)cache.get(key).get();
        }
        return null;
    }

    @Override
    public void evict(String key) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        cache.evict(key);
    }
}
