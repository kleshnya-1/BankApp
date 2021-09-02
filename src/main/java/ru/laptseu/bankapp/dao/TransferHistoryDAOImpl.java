package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.core.HibernateSessionFactoryUtil;
import ru.laptseu.bankapp.models.TransferHistory;

import java.sql.SQLException;

@Log4j2
//DB saves ID's of models in create().

public class TransferHistoryDAOImpl implements IMaintainableDAO<TransferHistory> {

    @Override
    public int create(TransferHistory obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
        //todo check is works
        return obj.getId();
    }

    @Override
    public TransferHistory read(int key) throws SQLException {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TransferHistory.class, key);
    }

    @Override
    public void update(TransferHistory obj) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(TransferHistory obj, Session s) throws SQLException {
        Session session = s;
        Transaction tx1 = session.beginTransaction();
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
    
   

/*        TransferHistory transferHistory = obj;
        try (Connection connection = new ConnectionMaker().makeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into transfers (source_client, target_client, source_bank, target_bank, amount, currency, date ) " +
                            "values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, transferHistory.getAccountSource().getId());
//            preparedStatement.setInt(2, transferHistory.getAccountTarget().getId());
//            preparedStatement.setInt(3, transferHistory.getBankSource().getId());
//            preparedStatement.setInt(4, transferHistory.getBankTarget().getId());
//            preparedStatement.setString(5, transferHistory.getAccountSource().getCurrency().toString());
            preparedStatement.setDouble(6, transferHistory.getAmount());
            //todo check working in DB
            preparedStatement.setString(7, transferHistory.getDate().toString());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transferHistory.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch
        (SQLException e) {
            log.error(e);
            throw e;
        }
        return transferHistory.getId();
    }

    //
    //method not for checking now. in progress
    //todo have to be fixed
    @Override
    public TransferHistory read(int key) throws SQLException {

        TransferHistory transferHistory = new TransferHistory();
//        try (Connection connection = new ConnectionMaker().makeConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "select * from accounts where id=?");
//            preparedStatement.setInt(1, key);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet == null) throw new ModelNotFountException();
//
//            transferHistory.setId(resultSet.getInt("id"));
//            transferHistory.setAccountSource(resultSet.getInt("source_client"));
//            transferHistory.setId(resultSet.getInt("id"));
//
//            transferHistory.set(resultSet.getInt("bank_id"));
//            transferHistory.setBankName(resultSet.getString("bank_name"));
//            transferHistory.setClientName(resultSet.getString("client_name"));
//            //todo. check in test currency
//            transferHistory.setCurrency(Currency.valueOf(resultSet.getString("currency")));
//            transferHistory.setAmount(resultSet.getDouble("amount"));
//        } catch (SQLException e) {
//            log.error(e);
//            throw e;
//        } catch (RuntimeException e) {
//            log.error(e);
//            throw e;
//        }
        return transferHistory;
    }*/
//    @Override
//    public Connection getSession() {
//        throw new UnsupportedOperationException();
//    }
}
