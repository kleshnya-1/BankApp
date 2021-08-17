package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferHistoryDAOImpl implements IMaintainableDAO<TransferHistory> {

    @Override
    public int create(TransferHistory obj) throws SQLException {
        TransferHistory transferHistory = obj;
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into transfers (from_c, to_c, from_b, to_b, amount, currency) " +
                            "values (?,?,?,?,?,?)");
            preparedStatement.setString(1, transferHistory.getAccountSource().getClientName());
            preparedStatement.setString(2, transferHistory.getAccountTarget().getClientName());
            preparedStatement.setString(3, transferHistory.getBankSource().getName());
            preparedStatement.setString(4, transferHistory.getBankTarget().getName());
            preparedStatement.setString(5, transferHistory.getAccountSource().getCurrency().toString());
            preparedStatement.setDouble(6, transferHistory.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        //todo have to be fixed
        return 0;
    }

    //todo have to be fixed
    @Override
    public TransferHistory read(int key) {
        return null;
    }

    //todo have to be fixed
    @Override
    public boolean update(TransferHistory obj) {
        return false;
    }

    //todo have to be fixed
    @Override
    public boolean delete(int key) {
        return false;
    }
}
