package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDaoImpl implements IMaintainableDAO {
    private final Connection connection = new ConnectionMaker().makeConnection();

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //todo have to be fixed
        return 0;
    }


    //todo have to be realised
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

    //todo must be updated later(?)
    @Override
    public boolean update(Object obj) {
        return false;
    }

    //todo must be updated later(?)
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
}
