package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractDao<T>  {

    abstract T save(T obj);

    abstract T read(int id);

    abstract void update(T obj);

    abstract void delete(T obj);
}
