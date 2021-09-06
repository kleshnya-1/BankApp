package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class ConnectionCreator {
    private static final String URL = "jdbc:postgresql://localhost:5432/BankAppDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";
    private static Connection connection;

    public Connection createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.error(e);
        }
        return connection;
    }

    //todo свести к одному методу. как-то
    public Connection createConnectionWithFalseAutoCommit() {
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(e);
        }
        return connection;
    }
}