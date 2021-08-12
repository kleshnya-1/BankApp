package ru.laptseu.bankApp.dao;

public interface IMaintainableDAO {

    //здесь появятся дженерики
    // остальное пока без изменений

    int create(Object obj);

    Object read(int key);

    boolean update(Object obj);

    boolean delete(int key);
}
