package ru.laptseu.bankapp.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IMaintainableDAO<T> {
    int create(T obj) throws SQLException;

    T read(int key) throws SQLException;

    boolean update(T obj) throws SQLException;

    boolean update(T obj, Connection conn) throws SQLException;

    boolean delete(int key) throws SQLException;

    Connection getConnection();

}
