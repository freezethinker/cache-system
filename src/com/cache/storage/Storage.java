package com.cache.storage;

import com.cache.exception.MissingException;
import com.cache.exception.StorageOverflowException;

import java.util.List;

/**
 * Created by karan.uppal on 13/04/21
 **/
public interface Storage<K, V> {

    void add(K k, V v) throws StorageOverflowException;

    V get(K k) throws MissingException;

    List<K> getAll();

    V remove(K k) throws MissingException;


}
