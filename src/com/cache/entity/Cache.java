package com.cache.entity;

import com.cache.eviction_policy.CacheEvictionPolicy;
import com.cache.exception.MissingException;
import com.cache.exception.StorageOverflowException;
import com.cache.storage.CacheStorage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karan.uppal on 13/04/21
 **/
public class Cache<K, V> {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private CacheStorage<K, V> cacheStorage;
    private CacheEvictionPolicy<K> cacheEvictionPolicy;

    public Cache(CacheStorage<K, V> cacheStorage, CacheEvictionPolicy<K> cacheEvictionPolicy) {
        this.cacheStorage = cacheStorage;
        this.cacheEvictionPolicy = cacheEvictionPolicy;
    }

    public void get(K k) {
        try {
            cacheStorage.get(k);
            cacheEvictionPolicy.access(k);
        } catch (MissingException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public void put(K k, V v) {
        try {
            cacheStorage.add(k, v);
            cacheEvictionPolicy.include(k);
        } catch (StorageOverflowException e) {
            LOGGER.log(Level.WARNING, "Cache filled, evicting.");
            K kToRemove = cacheEvictionPolicy.evict();
            remove(kToRemove);
            put(k, v);
        }
    }

    public void remove(K k) {
        try {
            cacheStorage.remove(k);
        } catch (MissingException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public List<K> getAll() {
        List<K> keys = cacheStorage.getAll();
        if (keys.size() <= 0) {
            LOGGER.log(Level.WARNING, "Empty cache!");
        }
        return keys;
    }

}
