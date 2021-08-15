package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferHistoryDAO implements IMaintainableDAO {
    private final Connection connection = new ConnectionMaker().makeConnection();

    @Override
    public int create(Object obj) {
        TransferHistory transferHistory = (TransferHistory) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into transfers (from_c, to_c, from_b, to_b, amount, currency) " +
                            "values (?,?,?,?,?,?)");
            preparedStatement.setString(1, transferHistory.getFromC());
            preparedStatement.setString(2, transferHistory.getToC());
            preparedStatement.setString(3, transferHistory.getFromB());
            preparedStatement.setString(4, transferHistory.getToB());
            preparedStatement.setString(5, transferHistory.getToA().getCurrency().toString());
            preparedStatement.setDouble(6, transferHistory.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //todo have to be fixed
        return 0;
    }


    //todo have to be fixed
    @Override
    public Object read(int key) {
        return null;
    }


    //todo have to be fixed
    @Override
    public boolean update(Object obj) {
        return false;
    }


    //todo have to be fixed
    @Override
    public boolean delete(int key) {
        return false;
    }
}
