package ru.laptseu.bankapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.hibernate.Session;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.MongoClientSetUp;

import java.sql.SQLException;

public class CurrencyRateDAOPgAndMongo implements IMaintainableDAO<CurrencyRate> {
    //todo. ask. без такой конфигурации hibernate не хочет дружить с объектами курса валюты.
    // он их не может принимать за сущности, так как не видит в своей базе данных. если он не видит их как сущности, он их не
    // тому сделано следующим обраpом: сохраняется в 2 БД, как монго, так и ПГ, а читает
    // только из монго. проверил - работает.
    MongoCollection currencyRatesMongo = MongoClientSetUp.getMongoCollection(CurrencyRate.class);

    @Override
    public int create(CurrencyRate obj) throws SQLException {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.save(obj);
//        tx1.commit();
//        session.close();
        currencyRatesMongo.insertOne(obj);
        return obj.getId();
    }

    @Override
    public CurrencyRate read(int key) throws SQLException {
        return (CurrencyRate) currencyRatesMongo.find(Filters.eq("_id", key)).first();
    }

    @Override
    public void update(CurrencyRate obj) throws SQLException {

    }

    @Override
    public void delete(int key) throws SQLException {

    }

    @Override
    public void update(CurrencyRate obj, Session conn) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Session getSession() {
        throw new UnsupportedOperationException();
    }
}
