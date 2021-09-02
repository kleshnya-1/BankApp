package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//
//
//not for checking now. in progress
//
//
//
//
@Log4j2
public class ClientDAOImpl implements IMaintainableDAO<Client> {
    private final Connection connection = new ConnectionMaker().makeConnection();

    @Override
    public int create(Client client) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into clients (name, is_natural_person) " +
                            "values (?,?)");
            preparedStatement.setString(1, client.getName());
            preparedStatement.setBoolean(2, client.isNaturalPerson());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            generatedKeys.close();
        } catch
        (SQLException e) {
            log.error(e);
            throw e;
        }
        return client.getId();
    }

    //todo have to be fixed
    @Override
    public Client read(int key) {
        return null;
    }

    //todo have to be fixed
    @Override
    public void update(Client obj) {
    }

    @Override
    public void update(Client obj, Connection conn) throws SQLException {

    }

    //todo have to be fixed
    @Override
    public void delete(int key) {
    }

    @Override
    public Connection getConnection() {
        return new ConnectionMaker().makeConnectionWithFalseAutoCommit();
    }

}
