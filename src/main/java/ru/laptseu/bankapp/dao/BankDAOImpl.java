package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.utilities.ConnectionMaker;

import java.sql.*;

@Log4j2
public class BankDAOImpl implements IMaintainableDAO<Bank> {
    //? no factory because of specific method
    CurrencyRateDAO currencyRateDAO = new CurrencyRateDAO();

    @Override
    public int create(Bank bank) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into banks (name, transfer_fee, transfer_fee_nnp, currency_rate, id)" +
                            "values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setDouble(2, bank.getTransferFeeInPercent());
            preparedStatement.setDouble(3, bank.getTransferFeeInPercentForNotNaturalPersons());
            preparedStatement.setInt(4, bank.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bank.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch
        (SQLException e) {
            log.error(e);
            throw e;
        }
        return bank.getId();
    }

    @Override
    public Bank read(int key) throws SQLException {
        ResultSet resultSet;
        Bank bank = new Bank();
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from banks where id=?");
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) throw new EntityNotFoundException();
                bank.setId(resultSet.getInt("id"));
                bank.setName(resultSet.getString("name"));
                bank.setTransferFeeInPercent(resultSet.getDouble("transfer_fee"));
                bank.setTransferFeeInPercentForNotNaturalPersons(resultSet.getDouble("transfer_fee_nnp"));
                //the method
                bank.setRateList(currencyRateDAO.readList(bank.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return bank;
    }

    @Override
    public void update(Bank bank) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update banks (name, transfer_fee, transfer_fee_nnp, id) " +
                            "set (?,?,?,?)");
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setDouble(2, bank.getTransferFeeInPercent());
            preparedStatement.setDouble(3, bank.getTransferFeeInPercentForNotNaturalPersons());
            preparedStatement.setInt(4, bank.getId());
            preparedStatement.executeUpdate();
        } catch
        (SQLException e) {
            log.error(e);
            throw e;
        }

    }

    @Override
    public void update(Bank obj, Connection conn) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int key) throws SQLException {

        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from banks where  id=?");
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            throw e;
        }

    }

    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException();
    }

}
