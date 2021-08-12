package ru.laptseu.bankApp.dao;

import ru.laptseu.bankApp.models.TransferHistory;
import ru.laptseu.bankApp.utilities.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransferHistoryDAO implements IMaintainableDAO {
    Connection connection = new ConnectionMaker().makeConnection();

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
        return 0;
    }

    @Override
    public Object read(int key) {
        return null;
    }

    public void readForLastDays(String clientName, int days) {


    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }

    public void showTransactionByNameAndDate(String name, int days) {

        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from transfers where client_name = ? and date > ?");
            preparedStatement.setString(1, name);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, -days * 24);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            preparedStatement.setString(2, format.format(calendar.getTime()));

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
                System.out.print(resultSet.getString("from_c") + "   ");
                System.out.print(resultSet.getString("from_b") + "   to ");
                System.out.print(resultSet.getString("to_c") + "   ");
                System.out.print(resultSet.getString("to_b") + "   ");
                System.out.print(resultSet.getDouble("amount") + " ");
                System.out.print(resultSet.getString("currency") + " \n");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

    }
}
