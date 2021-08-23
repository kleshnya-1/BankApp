package ru.laptseu.bankapp.services;

import java.sql.SQLException;

public interface IMaintainableService<T> {
    T create(String[] paramArr) throws SQLException;

    T read(int key) throws SQLException;

    boolean update(String[] paramArr) throws SQLException;

    boolean delete(int key) throws SQLException;
}
