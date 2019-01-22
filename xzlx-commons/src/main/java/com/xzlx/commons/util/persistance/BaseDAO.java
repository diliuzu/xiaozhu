package com.xzlx.commons.util.persistance;

public interface BaseDAO<T> {

    void insert(T t);

    void delete(T t);

    void update(T t);

    T selectById(Integer id);

}
