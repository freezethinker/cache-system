package com.cache.storage;

import com.cache.exception.MissingException;
import com.cache.exception.StorageOverflowException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by karan.uppal on 13/04/21
 **/
public class SingleTierStorage<K, V> implements Storage<K, V> {

    private int capacity;
    private Map<K, V> data;

    public SingleTierStorage(int capacity) {
        this.capacity = capacity;
        this.data = new HashMap<>();
    }

    @Override
    public void add(K k, V v) throws StorageOverflowException {
        if (capacity > data.size()) {
            data.put(k, v);
        } else {
            throw new StorageOverflowException();
        }
    }

    @Override
    public V get(K k) throws MissingException {
        V v = data.get(k);
        if (v == null)
            throw new MissingException();
        else return v;
    }

    @Override
    public List<K> getAll() {
        return new ArrayList<>(data.keySet());
    }

    @Override
    public V remove(K k) throws MissingException {
        V v = data.remove(k);
        if (v == null)
            throw new MissingException();
        return v;
    }
}
