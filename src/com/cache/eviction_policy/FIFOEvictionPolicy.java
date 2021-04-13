package com.cache.eviction_policy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by karan.uppal on 13/04/21
 **/
public class FIFOEvictionPolicy<K> implements EvictionPolicy<K> {

    private Queue<K> fifoKeys;

    public FIFOEvictionPolicy() {
        this.fifoKeys = new LinkedList<>();
    }

    @Override
    public void include(K k) {
        fifoKeys.add(k);
    }

    @Override
    public void access(K k) {
        // No change even after accessing a key
    }

    @Override
    public K evict() {
        return fifoKeys.poll();
    }
}
