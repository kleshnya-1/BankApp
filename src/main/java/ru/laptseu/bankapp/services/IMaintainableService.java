package ru.laptseu.bankapp.services;

import java.sql.SQLException;

public interface IMaintainableService<T> {
    // TODO: 09.09.2021 remove it
    T create(String[] paramArr) throws SQLException;

    int persist(T obj) throws SQLException;

    T read(int key) throws SQLException;

    // TODO: 09.09.2021 remove
    void update(String[] paramArr) throws SQLException;

    void update(T obj) throws SQLException;

    void delete(int key) throws SQLException;
}
