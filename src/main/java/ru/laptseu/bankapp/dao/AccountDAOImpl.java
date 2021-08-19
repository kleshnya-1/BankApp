package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.ModelNotFountException;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.*;

@Log4j2
public class AccountDAOImpl implements IMaintainableDAO<Account> {

    @Override
    public int create(Account acc) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into accounts (client_name, bank_id, currency, amount) " +
                            "values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, acc.getClientName());
            preparedStatement.setInt(2, acc.getBankId());
            preparedStatement.setString(3, acc.getCurrency().toString());
            preparedStatement.setDouble(4, acc.getAmount());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    acc.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch
        (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }

        return acc.getId();
    }

    @Override
    public Account read(int key) throws SQLException {
        Account account = new Account();
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from accounts where id=?");
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) throw new ModelNotFountException();
            account.setId(resultSet.getInt("id"));
            account.setBankId(resultSet.getInt("bank_id"));

            account.setClientName(resultSet.getString("client_name"));
            //todo. check in test currency
            account.setCurrency(Currency.valueOf(resultSet.getString("currency")));
            account.setAmount(resultSet.getDouble("amount"));
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return account;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        boolean result;
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update  accounts set amount= ?, name =?, bank_id=?, currency = ? where id=?");
            preparedStatement.setDouble(1, account.getAmount());
            preparedStatement.setString(2, account.getClientName());
            preparedStatement.setInt(3, account.getBankId());
            preparedStatement.setString(4, account.getCurrency().toString());
            preparedStatement.setInt(5, account.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }
        //todo is it good to return result as here?
        return result;
    }

    //method for transaction
    @Override
    public boolean update(Account account, Connection conn) throws SQLException {
        boolean result;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "update  accounts set amount= ?, name =?, bank_id=?, currency = ? where id=?");
            preparedStatement.setDouble(1, account.getAmount());
            preparedStatement.setString(2, account.getClientName());
            preparedStatement.setInt(3, account.getBankId());
            preparedStatement.setString(4, account.getCurrency().toString());
            preparedStatement.setInt(5, account.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        } finally {
            //close in service
            //conn.close();
        }
        return result;
    }

    @Override
    public boolean delete(int key) throws SQLException {
        boolean result;
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete  accounts where  id=?");
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }
        return result;
    }

    @Override
    public Connection getConnection() {
        return new ConnectionMaker().makeConnectionWithFalseAutoCommit();
    }

}
