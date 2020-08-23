package com.example.demo.service;

/**
 * @author ares
 */
public interface CacheService {

    /**
     * 插入缓存
     *
     * @param key   String
     * @param value Object
     */
    void setCommonCache(String key, Object value);

    /**
     * 获取缓存
     *
     * @param key String
     * @return Object
     */
    Object getCommonCache(String key);

    /**
     * 刷新缓存
     *
     * @param key
     */
    void refreshCache(String key);

    /**
     * 更新缓存
     *
     * @param key
     * @param value
     */
    void refreshCache(String key, Object value);

}
