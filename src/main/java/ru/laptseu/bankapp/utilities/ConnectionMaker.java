package ru.laptseu.bankapp.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
    private static final String URL = "jdbc:postgresql://localhost:5432/BankAppDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";
    private static Connection connection;

    public Connection makeConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }

    public Connection makeConnectionWithFalseAutoCommit() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }
}