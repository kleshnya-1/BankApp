package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

import java.sql.SQLException;

@Log4j2
public class AccountDAOImpl implements IMaintainableDAO<Account> {
    @Override
    public int create(Account obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
        //todo check is works
        return obj.getId();
    }

    @Override
    public Account read(int key) throws SQLException {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Account.class, key);
    }

    @Override
    public void update(Account obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        update(obj, session);
        //todo ask. thread-safe?
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Account obj, Session s) throws SQLException {
        Session session = s;
        if (!session.getTransaction().isActive()) session.beginTransaction();
        session.update(obj);
    }

    @Override
    public void delete(int key) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(key);
        tx1.commit();
        session.close();
    }

    @Override
    public Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    //clean JDBC
/*
    @Override
    public int create(Account acc) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into accounts (client_name, bank_id, currency, amount) " +
                            "values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, acc.getClientId());
            preparedStatement.setInt(2, acc.getBankId());
            preparedStatement.setString(3, acc.getCurrency().toString());
            preparedStatement.setDouble(4, acc.getAmount());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    acc.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
                generatedKeys.close();
        } catch
        (SQLException e) {
            log.error(e);
            throw e;
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
            //todo. close resultset
            if (resultSet == null) throw new EntityNotFoundException();
            account.setId(resultSet.getInt("id"));
            account.setBankId(resultSet.getInt("bank_id"));
            account.setClientId(resultSet.getInt("client_name"));
            //todo. check in test currency
            account.setCurrency(Currency.valueOf(resultSet.getString("currency")));
            account.setAmount(resultSet.getDouble("amount"));
        } catch (SQLException e) {
            log.error(e);
            throw e;
        } catch (EntityNotFoundException e) {
            log.error(e);
            throw e;
        }
        return account;
    }

    @Override
    public void update(Account account) throws SQLException {

         update(account, null);

    }

    //method for transaction
    //must be closed after usage
    @Override
    public void update(Account account, Connection conn) throws SQLException {
        Connection connection;
        if (conn != null) {
            connection = conn;
        } else {
            connection =  new ConnectionMaker().makeConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update  accounts set amount= ?, name =?, bank_id=?, currency = ? where id=?");
            preparedStatement.setDouble(1, account.getAmount());
            preparedStatement.setInt(2, account.getClientId());
            preparedStatement.setInt(3, account.getBankId());
            preparedStatement.setString(4, account.getCurrency().toString());
            preparedStatement.setInt(5, account.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            throw e;
        }
        if (conn==null) connection.close();

    }

    @Override
    public void delete(int key) throws SQLException {
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete  accounts where  id=?");
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
            //todo close???
        } catch (SQLException e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public Connection getConnection() {
        return new ConnectionMaker().makeConnectionWithFalseAutoCommit();
    }
*/

}
