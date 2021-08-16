package ru.laptseu.bankapp.dao;

public interface IMaintainableDAO {

    //todo generics

    int create(Object obj);

    Object read(int key);

    boolean update(Object obj);

    boolean delete(int key);
}
