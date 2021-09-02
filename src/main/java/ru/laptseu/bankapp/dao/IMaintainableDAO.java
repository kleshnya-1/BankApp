package ru.laptseu.bankapp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.EntityModel;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

import java.sql.SQLException;

public interface IMaintainableDAO<T extends EntityModel> {

    default int save(T obj) throws SQLException {
        //todo ref and reuse it
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(obj);
            tx1.commit();
        } finally {
            session.close();
            return obj.getId();
        }
    }
//todo fix by default
    //T read(int key);

    default void update(T obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        update(obj, session);
        session.getTransaction().commit();
        session.close();
    }

    default void update(T obj, Session conn) throws SQLException {
        Session session = conn;
        if (!session.getTransaction().isActive()) session.beginTransaction();
        session.update(obj);
    }

    default void delete(int key) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.delete(key);
            tx1.commit();
        } finally {
            session.close();
        }
    }

    default void delete(Bank key) {
        Session session = getSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.delete(key);
            tx1.commit();;
        } finally {
            session.close();
        }
    }

    default Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    //todo fix if its possible for read()
    default T read(int key) {
       Class c = this.getClass();
        return (T) HibernateSessionFactoryUtil.getSessionFactory().openSession().get(this.getClass(), key);
    }
}
