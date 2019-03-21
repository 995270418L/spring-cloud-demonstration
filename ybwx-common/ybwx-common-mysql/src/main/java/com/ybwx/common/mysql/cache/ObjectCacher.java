package com.ybwx.common.mysql.cache;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ObjectCacher {
    public static final long TIME_OUT = 300;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private Logger logger = LoggerFactory.getLogger(ObjectCacher.class);
    private RedisTemplate redisTemplate;
    //    @Value("${spring.redis.api.key.prefix:}")
    private String keyPrefix;

    public ObjectCacher(RedisTemplate redisTemplate, String keyPrefix) {
        this.keyPrefix = keyPrefix;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取key
     *
     * @param key
     * @return
     */
    public String getKey(Object key) {
        String k;
        if (key instanceof String) {
            k = (String) key;
        } else {
            k = JSON.toJSONString(key);
        }

        if (!k.startsWith(keyPrefix)) {
            if (k.startsWith("::")) {
                k = this.keyPrefix + k;
            } else {
                k = this.keyPrefix + "::" + k;
            }
        }
        return k;
    }

    public String resolveKey(String customPrefix, Object key) {
        String k;
        if (key instanceof String) {
            k = (String) key;
        } else {
            k = JSON.toJSONString(key);
        }

        if (!k.startsWith(keyPrefix)) {
            if (k.startsWith("::")) {
                k = this.keyPrefix + "::" + customPrefix + k;
            } else {
                k = this.keyPrefix + "::" + customPrefix + "::" + k;
            }
        }
        return k;
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     */
    public void add(Object key, Object value) {
        add(key, value, TIME_OUT, TIME_UNIT);
    }

    public void add(Object key, Object value, long timeout) {
        add(key, value, timeout, TIME_UNIT);
    }

    public void addNX(Object key, Object value) {
        addNX(key, value, TIME_OUT, TIME_UNIT);
    }

    public void addNX(Object key, Object value, long timeout) {
        addNX(key, value, timeout, TIME_UNIT);
    }

    public void add(Object key, Object value, long timeout, TimeUnit timeUnit) {
        String k = getKey(key), v;
        try {

            if (timeout > 0) {
                redisTemplate.opsForValue().set(k, value, timeout, timeUnit);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void addNX(Object key, Object value, long timeout, TimeUnit timeUnit) {
        try {
            String k = getKey(key);
            if (timeout > 0) {
                redisTemplate.opsForValue().setIfAbsent(k, value);
                redisTemplate.expire(k, timeout, timeUnit);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void remove(Object key) {
        String k;
        try {
            if (key instanceof String && ((String) key).startsWith(keyPrefix)) {
                k = (String) key;
            } else {
                k = getKey(key);
            }
            redisTemplate.delete(k);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取所有的key，正则
     *
     * @param keyPattern
     * @return
     */
    public Set<String> keys(String keyPattern) {
        return redisTemplate.keys(getKey(keyPattern));
    }

    /**
     * 移除所有的key，正则
     *
     * @param keyPattern
     */
    public void removes(String keyPattern) {
        Set<String> keys = redisTemplate.keys(getKey(keyPattern));
        if (keys != null && !keys.isEmpty()) {
            try {
                redisTemplate.delete(keys);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @param defaultVal 默认值
     * @return
     */
    public Object get(Object key, String defaultVal) {
        Object result = get(key);
        if (result == null) {
            return defaultVal;
        } else {
            return result;
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        Object value = null;
        String k = getKey(key);
        try {
            value = redisTemplate.opsForValue().get(k);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return value;
    }


    /**
     * 递增
     *
     * @param key
     * @param count
     * @return
     */
    public Long increment(Object key, long count) {
        return increment(key, count, TIME_OUT);
    }

    public Long increment(Object key, long count, long timeout) {
        return increment(key, count, timeout, TIME_UNIT);
    }

    public Long increment(Object key, long count, long timeout, TimeUnit timeUnit) {
        String k = getKey(key);
        Long result = redisTemplate.opsForValue().increment(k, count);
        if (timeout > 0) {
            redisTemplate.expire(k, timeout, timeUnit);
        }
        return result;
    }

    public Double increment(Object key, double count) {
        return increment(key, count, TIME_OUT);
    }

    public Double increment(Object key, double count, long timeout) {
        return increment(key, count, timeout, TIME_UNIT);
    }

    public Double increment(Object key, double count, long timeout, TimeUnit timeUnit) {
        String k = getKey(key);
        Double result = redisTemplate.opsForValue().increment(k, count);
        if (timeout > 0) {
            redisTemplate.expire(k, timeout, timeUnit);
        }
        return result;
    }

    /**
     * 过期时间
     *
     * @param key
     * @param timeout
     */
    public void expire(Object key, long timeout) {
        expire(key, timeout, TIME_UNIT);
    }

    public void expire(Object key, long timeout, TimeUnit timeUnit) {
        String k = getKey(key);
        if (timeout > 0) {
            redisTemplate.expire(k, timeout, timeUnit);
        }
    }

    /**
     * 过期时间
     *
     * @param key
     * @param date
     */
    public void expireAt(Object key, Date date) {
        String k = getKey(key);
        if (date != null) {
            redisTemplate.expireAt(k, date);
        }
    }

    public int getSize(String pattern) {
        return keys(pattern).size();
    }
}
