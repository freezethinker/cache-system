package com.cache.eviction_policy;

/**
 * Created by karan.uppal on 13/04/21
 **/
public interface EvictionPolicy<K> {

    void include(K k);

    void access(K k);

    K evict();

}
