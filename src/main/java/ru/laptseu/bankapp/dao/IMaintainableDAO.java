package ru.laptseu.bankapp.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IMaintainableDAO<T> {
    int create(T obj) throws SQLException;

    T read(int key) throws SQLException;

    void update(T obj) throws SQLException;

    void update(T obj, Connection conn) throws SQLException;

    void delete(int key) throws SQLException;

    Connection getConnection();

}
