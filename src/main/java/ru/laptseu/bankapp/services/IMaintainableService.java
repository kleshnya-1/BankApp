package ru.laptseu.bankapp.services;

import java.sql.SQLException;

public interface IMaintainableService<T> {
    T create(String[] paramArr) throws SQLException;

    int persist(T obj) throws SQLException;

    T read(int key) throws SQLException;

    void update(String[] paramArr) throws SQLException;

    void delete(int key) throws SQLException;
}
