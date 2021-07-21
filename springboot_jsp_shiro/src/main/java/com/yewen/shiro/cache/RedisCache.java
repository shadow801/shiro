package com.yewen.shiro.cache;

import com.yewen.shiro.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 自定义redis缓存的实现
 * @author ShadowStart
 * @create 2021-07-21 13:52
 */
public class RedisCache<K, V> extends MyByteSource implements Cache<K, V>, Serializable {

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key: " + k);
        return (V) getRedisTemplate().opsForHash().get(cacheName, k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key: " + k);
        System.out.println("put value: " + v);
        getRedisTemplate().opsForHash().put(cacheName, k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        System.out.println("====================remove====================");
        return (V) getRedisTemplate().opsForHash().delete(cacheName, k.toString());
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("====================clear====================");
        getRedisTemplate().delete(cacheName); // 直接将map给删了
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(cacheName);
    }

    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
