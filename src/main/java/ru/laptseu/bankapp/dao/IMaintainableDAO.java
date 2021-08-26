package ru.laptseu.bankapp.dao;

import org.hibernate.Session;

import java.sql.SQLException;

public interface IMaintainableDAO<T> {
    int create(T obj) throws SQLException;

    T read(int key) throws SQLException;

    void update(T obj) throws SQLException;

    void delete(int key) throws SQLException;

    void update(T obj, Session conn) throws SQLException;

    Session getSession();

//jdbc
    /*void update(T obj, Connection conn) throws SQLException;

    Connection getConnection();
*/
}
