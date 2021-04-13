package com.cache.entity;

import com.cache.eviction_policy.EvictionPolicy;
import com.cache.exception.MissingException;
import com.cache.exception.StorageOverflowException;
import com.cache.storage.Storage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karan.uppal on 13/04/21
 **/
public class Cache<K, V> {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private Storage<K, V> storage;
    private EvictionPolicy<K> evictionPolicy;

    public Cache(Storage<K, V> storage, EvictionPolicy<K> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void get(K k) {
        try {
            storage.get(k);
            evictionPolicy.access(k);
        } catch (MissingException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public void put(K k, V v) {
        try {
            storage.add(k, v);
            evictionPolicy.include(k);
        } catch (StorageOverflowException e) {
            LOGGER.log(Level.WARNING, "Cache filled, evicting.");
            K kToRemove = evictionPolicy.evict();
            remove(kToRemove);
            put(k, v);
        }
    }

    public void remove(K k) {
        try {
            storage.remove(k);
        } catch (MissingException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public List<K> getAll() {
        List<K> keys = storage.getAll();
        if (keys.size() <= 0) {
            LOGGER.log(Level.WARNING, "Empty cache!");
        }
        return keys;
    }

}
