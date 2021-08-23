package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.ModelNotFoundException;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//not implements interface
@Log4j2
public class CurrencyRateDAO {

    public int create(CurrencyRate currencyRate) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into currency_rate values ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, currencyRate.getBankId());
            preparedStatement.setString(2, currencyRate.getCurrency().toString());
            preparedStatement.setDouble(3, currencyRate.getRateToByn());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    currencyRate.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating currency_rate failed, no ID obtained.");
                }
            }
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }
        return currencyRate.getId();
    }

    //todo list...
    public List<CurrencyRate> readList(int key) throws SQLException {
        List currencyList = new ArrayList();
        CurrencyRate currencyRate = new CurrencyRate();
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from currency_rate where bank_id=?");
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) throw new ModelNotFoundException();
            while (resultSet.next()) {
                currencyRate.setBankId(resultSet.getInt("bank_id"));
                currencyRate.setCurrency(Currency.valueOf(resultSet.getString("currency")));
                currencyRate.setRateToByn(resultSet.getDouble("rate_to_byn"));
                currencyList.add(currencyRate);
            }
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return currencyList;
    }

    public boolean update(CurrencyRate currencyRate) throws SQLException {
        boolean result;
        try (Connection connectionManualCommit = new ConnectionMaker().makeConnectionWithFalseAutoCommit()) {
            PreparedStatement preparedStatement = connectionManualCommit.prepareStatement(
                    "update  currency_rate set amount= ? where bank_id=? and currency=?");
            preparedStatement.setInt(1, currencyRate.getBankId());
            preparedStatement.setString(2, currencyRate.getCurrency().toString());
            //todo check exception while no entity found and add to catch
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }
        return result;
    }

    public boolean delete(CurrencyRate currencyRate) throws SQLException {
        boolean result;
        try (Connection connectionManualCommit = new ConnectionMaker().makeConnectionWithFalseAutoCommit()) {
            PreparedStatement preparedStatement = connectionManualCommit.prepareStatement(
                    "delete  currency_rate where bank_id=? and currency=?");
            preparedStatement.setInt(1, currencyRate.getBankId());
            preparedStatement.setString(2, currencyRate.getCurrency().toString());
            //todo check exception while no entity found
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }
        return result;
    }

}
