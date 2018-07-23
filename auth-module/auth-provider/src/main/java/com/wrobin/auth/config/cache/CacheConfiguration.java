package com.wrobin.auth.config.cache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager();
    }
}
