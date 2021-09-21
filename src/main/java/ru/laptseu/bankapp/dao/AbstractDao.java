package ru.laptseu.bankapp.dao;

public abstract class AbstractDao<T> {

    abstract T create(T obj) ;

    abstract T read(int id) ;

    abstract void update(T obj) ;

    abstract void delete(T obj);
}
