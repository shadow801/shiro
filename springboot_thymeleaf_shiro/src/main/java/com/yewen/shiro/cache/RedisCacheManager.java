package com.yewen.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 自定义的shiro的缓存管理器
 * @author ShadowStart
 * @create 2021-07-21 13:46
 */
public class RedisCacheManager implements CacheManager {

    /**
     * @param cacheName 认证或者是授权缓存的统一名称
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println(cacheName);
        return new RedisCache<>(cacheName);
    }

}
