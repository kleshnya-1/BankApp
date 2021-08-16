package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoImpl implements IMaintainableDAO<Client> {
    private final Connection connection = new ConnectionMaker().makeConnection();

    @Override
    public int create(Client client) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into clients (name, is_natural_person) " +
                            "values (?,?)");
            preparedStatement.setString(1, client.getName());
            preparedStatement.setBoolean(2, client.isNaturalPerson());
            preparedStatement.executeUpdate();
            System.out.println("Клиент " + client.getName() + " добавлен");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        return 0;
    }

    //todo have to be fixed
    @Override
    public Client read(int key) {
        return null;
    }

    public Client readByName(String name) throws SQLException {
        ResultSet resultSet;
        Client client = new Client();
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from banks where name =?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            client.setName(resultSet.getString("name"));
            client.setNaturalPerson(resultSet.getBoolean("is_natural_person"));
            client.setId(resultSet.getInt("id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        return client;
    }

    //todo have to be fixed
    @Override
    public boolean update(Client obj) {
        return false;
    }

    //todo have to be fixed
    @Override
    public boolean delete(int key) {
        return false;
    }
}
