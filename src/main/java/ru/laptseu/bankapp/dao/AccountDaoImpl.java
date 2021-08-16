package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements IMaintainableDAO<Account> {
    Connection connectionManualCommit = new ConnectionMaker().makeConnectionWithFalseAutoCommit();

    @Override
    public int create(Account acc) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into accounts (client_name, bank_name, currency, amount) " +
                            "values (?,?,?,?)");
            preparedStatement.setString(1, acc.getClientName());
            preparedStatement.setString(2, acc.getBankName());
            preparedStatement.setString(3, acc.getCurrency().toString());
            preparedStatement.setDouble(4, acc.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        //todo have to be fixed.
        // i don't know how to return entity ID
        return 0;
    }

    //todo have to be fixed
    @Override
    public Account read(int key) {
        return new Account();
    }

    //todo refactor with objects but strings
    public Account readByName(String name, String bank) throws SQLException {
        Account account = new Account();
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from accounts where client_name =? and bank_name=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, bank);
            ResultSet resultSet = preparedStatement.executeQuery();
            account.setId(resultSet.getInt("id"));
            account.setBankId(resultSet.getInt("bank_id"));
            account.setBankName(resultSet.getString("bank_name"));
            account.setClientName(resultSet.getString("client_name"));

            Currency chosedCurrency = null;
            switch (resultSet.getString("currency")) {
                case "BYN":
                    chosedCurrency = Currency.BYN;
                    break;
                case "USD":
                    chosedCurrency = Currency.USD;
                    break;
                case "EUR":
                    chosedCurrency = Currency.EUR;
                    break;
            }
            account.setCurrency(chosedCurrency);
            account.setAmount(resultSet.getDouble("amount"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        return account;
    }

    // todo how to close class if it's part of transaction?
    @Override
    public boolean update(Account account) {

        try {
            PreparedStatement preparedStatement = connectionManualCommit.prepareStatement(
                    "update  accounts set amount= ? whete id=?");
            preparedStatement.setDouble(1, account.getAmount());
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //todo have to be fixed
        return false;
    }

    //todo in progress
    @Override
    public boolean delete(int key) {
        return false;
    }

    public void commit() {
        try {
            connectionManualCommit.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
