package com.exozet.android.core.interfaces;

import java.util.List;

public interface StorageProvider<T> {

    boolean create(T t);

    List<T> read();

    boolean delete(T t);

    void clear();

    boolean contains(T t);
}