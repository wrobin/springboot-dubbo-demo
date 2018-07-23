package com.wrobin.user.config.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 * created by robin.wu on 2018/5/3
 **/
@Component
public class CacheUtil {

    @Autowired
    @Qualifier("cacheManager")
    private EhCacheCacheManager cacheManager;

    public void put(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.put(key,value);
    }

    public Object get(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if(cache.get(key) !=null) {
            return cache.get(key).get();
        }
        return null;
    }

    public Cache get(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    public void evict(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.evict(key);
    }
}
