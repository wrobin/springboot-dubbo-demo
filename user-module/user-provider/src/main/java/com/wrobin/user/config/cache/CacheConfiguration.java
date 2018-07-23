package com.wrobin.user.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager();
    }
}
