package ru.laptseu.bankApp.dao;

import ru.laptseu.bankApp.models.Bank;
import ru.laptseu.bankApp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDaoImpl implements IMaintainableDAO {
    Connection connection = new ConnectionMaker().makeConnection();

    @Override
    public int create(Object obj) {
        Bank bank = (Bank) obj;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into banks (name, transfer_fee, transfer_fee_nnp, USD_rate, EUR_rate) " +
                            "values (?,?,?,?,?)");
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setDouble(2, bank.getTransferFeeInPercent());
            preparedStatement.setDouble(3, bank.getTransferFeeInPercentForNotNaturalPersons());

            preparedStatement.setDouble(4, bank.getUSDrate());
            preparedStatement.setDouble(5, bank.getEURrate());

            preparedStatement.executeUpdate();
            System.out.println("Банк " + bank.getName() + " добавлен");


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
        Bank bank = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from banks where name=?");

            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    bank.setId(resultSet.getInt("id"));
                    bank.setName(resultSet.getString("name"));
                    bank.setTransferFeeInPercent(resultSet.getDouble("transfer_fee"));
                    bank.setTransferFeeInPercentForNotNaturalPersons(resultSet.getDouble("transfer_fee_nnp"));
                    bank.setUSDrate(resultSet.getDouble("usd_rate"));
                    bank.setEURrate(resultSet.getDouble("eur_rate"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bank;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }

    public ResultSet getAllNames() {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select name from banks");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;

    }

    public void showAllNames(

    ) {
        ResultSet resultSet = getAllNames();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                System.out.println(resultSet.getString("name"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

    }
}
