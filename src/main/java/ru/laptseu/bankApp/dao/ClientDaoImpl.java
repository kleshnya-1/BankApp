package ru.laptseu.bankApp.dao;

import ru.laptseu.bankApp.models.Client;
import ru.laptseu.bankApp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoImpl implements IMaintainableDAO {
    Connection connection = new ConnectionMaker().makeConnection();

    public void showAccountsByName(String name) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from accounts where client_name = ?");
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Аккаунты на имя " + name);
        System.out.println("Банк валюта остаток");
        while (true) {

            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                System.out.print(resultSet.getString("bank_name") + "   ");
                System.out.print(resultSet.getString("currency") + "   ");
                System.out.print(resultSet.getDouble("amount") + "   \n");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

    }

    @Override
    public int create(Object obj) {

        Client client = (Client) obj;


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into clients (name, is_natural_person) " +
                            "values (?,?)");


            preparedStatement.setString(1, client.getName());
            preparedStatement.setBoolean(2, client.isNaturalPerson());


            preparedStatement.executeUpdate();
            System.out.println("Клиент " + client.getName() + " добавлен");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return 0;
    }

    @Override
    public Object read(int key) {
        return null;
    }

    public Object readByName(String name) {
        ResultSet resultSet = null;
        Client client = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from banks where name =?");

            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();


            client.setName(resultSet.getString("name"));
            client.setNaturalPerson(resultSet.getBoolean("is_natural_person"));
            client.setId(resultSet.getInt("id"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
