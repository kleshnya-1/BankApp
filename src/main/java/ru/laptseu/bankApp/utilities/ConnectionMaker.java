package ru.laptseu.bankApp.utilities;

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
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;

    }

    public Connection makeConnectionWithFalseAutoCommit() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }

}