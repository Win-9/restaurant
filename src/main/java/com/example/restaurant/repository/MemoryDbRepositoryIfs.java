package com.example.restaurant.repository;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T>{
    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> findAll();
}
