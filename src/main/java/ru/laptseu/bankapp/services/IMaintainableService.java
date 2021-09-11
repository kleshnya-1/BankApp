package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;

public interface IMaintainableService<T> {
    int persist(T obj) throws SQLException;

    T read(int key) throws SQLException;

    T read(Currency currency, int key) throws SQLException;

    void update(T obj) throws SQLException;

    void delete(int key) throws SQLException;
}
