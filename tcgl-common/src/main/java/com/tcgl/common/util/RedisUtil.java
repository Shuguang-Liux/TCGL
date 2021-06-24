package com.tcgl.common.util;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 *
 * @author CL
 *
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static RedisUtil redisUtil;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        redisUtil = this;
        redisUtil.redisTemplate = this.redisTemplate;
    }

    /**
     * 查询key，支持模糊查询
     *
     * @param key
     */
    public static Set<String> keys(String key) {
        return redisUtil.redisTemplate.keys(key);
    }

    /**
     * 获取值
     *
     * @param key
     */
    public static Object get(String key) {
        return redisUtil.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        redisUtil.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置值，并设置过期时间
     *
     * @param key
     * @param value
     * @param expire 过期时间，单位秒
     */
    public static void set(String key, String value, Integer expire) {
        redisUtil.redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 删出key
     *
     * @param key
     */
    public static void delete(String key) {
        redisUtil.redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 设置对象
     *
     * @param key     key
     * @param hashKey hashKey
     * @param object  对象
     */
    public static void hset(String key, String hashKey, Object object) {
        redisUtil.redisTemplate.opsForHash().put(key, hashKey, object);
    }

    /**
     * 设置对象
     *
     * @param key     key
     * @param hashKey hashKey
     * @param object  对象
     * @param expire  过期时间，单位秒
     */
    public static void hset(String key, String hashKey, Object object, Integer expire) {
        redisUtil.redisTemplate.opsForHash().put(key, hashKey, object);
        redisUtil.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置HashMap
     *
     * @param key key
     * @param map map值
     */
    public static void hset(String key, HashMap<String, Object> map) {
        redisUtil.redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * key不存在时设置值
     *
     * @param key
     * @param hashKey
     * @param object
     */
    public static void hsetAbsent(String key, String hashKey, Object object) {
        redisUtil.redisTemplate.opsForHash().putIfAbsent(key, hashKey, object);
    }

    /**
     * 获取Hash值
     *
     * @param key
     * @param hashKey
     * @return
     */
    public static Object hget(String key, String hashKey) {
        return redisUtil.redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key的所有值
     *
     * @param key
     * @return
     */
    public static Object hget(String key) {
        return redisUtil.redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除key的所有值
     *
     * @param key
     */
    public static void deleteKey(String key) {
        redisUtil.redisTemplate.opsForHash().getOperations().delete(key);
    }

    /**
     * 判断key下是否有值
     *
     * @param key
     */
    public static Boolean hasKey(String key) {
        return redisUtil.redisTemplate.opsForHash().getOperations().hasKey(key);
    }

    /**
     * 判断key和hasKey下是否有值
     *
     * @param key
     * @param hasKey
     */
    public static Boolean hasKey(String key, String hasKey) {
        return redisUtil.redisTemplate.opsForHash().hasKey(key, hasKey);
    }

}