package com.cache;

import com.cache.entity.Cache;
import com.cache.eviction_policy.FIFOCacheEvictionPolicy;
import com.cache.eviction_policy.LRUCacheEvictionPolicy;
import com.cache.storage.SingleTierCacheStorage;

public class Main {

    public static void main(String[] args) {

        Cache fifoCache = new Cache<String, String>(new SingleTierCacheStorage<>(5), new FIFOCacheEvictionPolicy<>());
        fifoCache.put("Key1", "Value1");
        fifoCache.put("Key2", "Value2");
        fifoCache.put("Key3", "Value3");
        fifoCache.put("Key4", "Value4");
        fifoCache.get("Key1"); // Key1 accessed, makes no difference in eviction
        fifoCache.get("Key3"); // Key3 accessed, makes no difference in eviction
        fifoCache.put("Key5", "Value5");
        fifoCache.put("Key6", "Value6"); // Key1 will be removed to put Key6
        fifoCache.put("Key7", "Value7"); // Key2 will be removed to put Key7
        System.out.println("FIFO Cache Keys: " + fifoCache.getAll());

        Cache lruCache = new Cache<String, String>(new SingleTierCacheStorage<>(5), new LRUCacheEvictionPolicy<>());
        lruCache.put("Key1", "Value1");
        lruCache.put("Key2", "Value2");
        lruCache.put("Key3", "Value3");
        lruCache.put("Key4", "Value4");
        lruCache.get("Key1"); // Key1 accessed, makes it most recently used
        lruCache.get("Key3"); // Key3 accessed, makes it most recently used
        lruCache.put("Key5", "Value5");
        lruCache.put("Key6", "Value6"); // Key2 will be removed to put Key6
        lruCache.put("Key7", "Value7"); // Key4 will be removed to put Key7
        System.out.println("LRU Cache Keys: " + lruCache.getAll());

    }
}
