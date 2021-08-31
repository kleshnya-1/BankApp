package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

import java.sql.SQLException;

@Log4j2
public class ClientDAOImpl implements IMaintainableDAO<Client> {

    @Override
    public int create(Client obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
        //todo check is works
        return obj.getId();
    }

    @Override
    public Client read(int key) throws SQLException {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, key);
    }

    @Override
    public void update(Client obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        update(obj, session);
        //todo ask. thread-safe?
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Client obj, Session s) throws SQLException {
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
}
