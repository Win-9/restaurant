package com.example.restaurant.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if (optionalEntity.isEmpty()) {//db에 값이 없는경우
            index++;
            entity.setIndex(index);
            db.add(entity);

            return entity;
        }
        else {
            int index = optionalEntity.get().getIndex();
            entity.setIndex(index);

            deleteById(index);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if (optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
