package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.core.HibernateSessionFactoryUtil;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;
import java.util.List;

//have one un-implemented methods readList(int bank id) and read (int bankId, Currency currency)
@Log4j2
public class CurrencyRateDAO implements IMaintainableDAO<CurrencyRate> {

    @Override
    public int create(CurrencyRate obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
        //todo check is works
        return obj.getId();
    }

    @Override
    public CurrencyRate read(int key) throws SQLException {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CurrencyRate.class, key);
    }

    //un-implemented method


    //doesnt work in jdbc
    //todo it doesnt work
    public List<CurrencyRate> readListByBankId(int bankKey) throws SQLException {
        String hql = "from CurrencyRate where bankid =:bankid2";
        List<CurrencyRate> result = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery(hql).setParameter("bankid2", bankKey).list();
        return result;
    }

    //todo check HQL
    public CurrencyRate read(int bankKey, Currency currency) throws SQLException {
        String hql = "from CurrencyRate  c where c.bankId =:bankId and c.currency =:currency";
        CurrencyRate currencyRateForReturning = (CurrencyRate) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(hql)
                .setParameter("bankId", bankKey)
                .setParameter("currency", currency).stream().findAny().orElse(null);
        return currencyRateForReturning;

    }

    @Override
    public void update(CurrencyRate obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        update(obj, session);
        //todo ask. thread-safe?
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(CurrencyRate obj, Session s) throws SQLException {
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



   /* public int create(CurrencyRate currencyRate) throws SQLException {
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
        } catch (SQLException e) {
            log.error(e);
            throw e;
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
            if (resultSet == null) throw new EntityNotFoundException();
            while (resultSet.next()) {
                currencyRate.setBankId(resultSet.getInt("bank_id"));
                currencyRate.setCurrency(Currency.valueOf(resultSet.getString("currency")));
                currencyRate.setRateToByn(resultSet.getDouble("rate_to_byn"));
                currencyList.add(currencyRate);
            }
        } catch (SQLException e) {
            log.error(e);
            throw e;
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
        } catch (SQLException e) {
            log.error(e);
            throw e;
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
        } catch (SQLException e) {
            log.error(e);
            throw e;
        }
        return result;
    }*/

}
