package com.cache.eviction_policy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by karan.uppal on 13/04/21
 **/
public class LRUCacheEvictionPolicy<K> implements CacheEvictionPolicy<K> {

    private Queue<K> lruKeys;

    public LRUCacheEvictionPolicy() {
        this.lruKeys = new LinkedList<>();
    }

    @Override
    public void include(K k) {
        lruKeys.add(k);
    }

    @Override
    public void access(K k) {
        // Remove and add to the last of the queue,
        // which will refresh this key as most recently used
        lruKeys.remove(k);
        lruKeys.add(k);
    }

    @Override
    public K evict() {
        return lruKeys.poll();
    }
}
