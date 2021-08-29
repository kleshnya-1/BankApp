package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;
import ru.laptseu.bankapp.models.TransferHistory;

import java.sql.SQLException;

@Log4j2
public class TransferHistoryDAOImpl implements IMaintainableDAO<TransferHistory> {

    @Override
    public int create(TransferHistory obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
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
//объект создается в 2 этапа, чтоб, в случае неудачной транзакции, это было зафиксированно в истории
    @Override
    public void update(TransferHistory obj, Session s) throws SQLException {
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
}
