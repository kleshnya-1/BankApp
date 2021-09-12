package ru.laptseu.bankapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.laptseu.bankapp.models.EntityModel;

import java.sql.SQLException;

public interface IMaintainableDAO<T extends EntityModel> {

    SessionFactory getSessionFactory();
    //todo ask можно сразу на getSession написать.
    // getSession(){
    // return getSessionFactory().openSession()
    // }
    // Это +поле, но в DAOImpl самих короче. есть в этом смысл?

    //todo DRY it
    default int save(T obj) throws SQLException {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        }
        return obj.getId();
    }

    default void update(T obj) throws SQLException {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            update(obj, session);
            session.getTransaction().commit();
        }
    }

    default void update(T obj, Session session) throws SQLException {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(obj);
    }

    default void delete(int key) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(key);
            session.getTransaction().commit();
        }
    }

    default void delete(T key) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(key);
            session.getTransaction().commit();
        }
    }

    //todo ask. могe его по аналогии с сессией организовать.
    // но мне это не очень нравится. как можно пулучить класс от этого дженерика
    // и естьл и вэтом смысл?
    T read(int key);
}
