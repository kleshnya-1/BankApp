package ru.laptseu.bankapp.dao;

public interface IMaintainableDAO<T> {
    int create(T obj);
    T read(int key);
    boolean update(T obj);
    boolean delete(int key);
}
