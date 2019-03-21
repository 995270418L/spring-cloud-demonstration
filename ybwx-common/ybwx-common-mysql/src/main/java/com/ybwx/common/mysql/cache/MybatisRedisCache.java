package com.ybwx.common.mysql.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class MybatisRedisCache implements Cache {

    @Autowired
    private ObjectCacher cacher;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    String CACHE_POSTFIX;
    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("必须传入ID");
        }
        log.info("MybatisRedisCache:id=" + id);
        this.id = id;
        CACHE_POSTFIX = "mybatis:" + id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log.info("put");
        cacher.add(cacher.resolveKey(CACHE_POSTFIX, key.toString()), value);
    }

    @Override
    public Object getObject(Object key) {
        log.info("get");
        return cacher.get(cacher.resolveKey(CACHE_POSTFIX, key.toString()));
    }

    @Override
    public Object removeObject(Object key) {
        log.info("remove");
        printStatck();
        cacher.remove(cacher.resolveKey(CACHE_POSTFIX, key.toString()));
        return 1;
    }

    @Override
    public void clear() {
        cacher.removes(CACHE_POSTFIX + "*");
    }

    @Override
    public int getSize() {
        return cacher.getSize(CACHE_POSTFIX + "*");
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        log.info("getReadWriteLock");
        return readWriteLock;
    }

    private void printStatck() {
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                log.info("[StackTrace-{}]:{} -- {} -- {} -- {}", i, stackElements[i].getClassName(), stackElements[i].getFileName(), stackElements[i].getLineNumber(),
                        stackElements[i].getMethodName());
            }
        }
    }
}
