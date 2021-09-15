package ru.laptseu.bankapp.services;

public interface IMaintainableService<T> {

    int save(T obj);

    T read(int key) throws Throwable;

    void update(T obj);

    void delete(int key);
}
