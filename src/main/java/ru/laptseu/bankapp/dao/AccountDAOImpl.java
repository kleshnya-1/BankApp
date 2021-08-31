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
}
