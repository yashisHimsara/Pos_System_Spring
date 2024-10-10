package com.example.possystemspring.dao;

import java.util.List;

public interface CrudDAO <T>{

    void save(T dto);
    void delete(String id);
    void update(String id, T dto);
    T get(String id);
    List<T> getAll();
}
