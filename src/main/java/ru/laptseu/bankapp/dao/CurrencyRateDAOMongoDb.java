package ru.laptseu.bankapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.hibernate.Session;
import ru.laptseu.bankapp.utilities.MongoClientSetUp;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

public class CurrencyRateDAOMongoDb implements IMaintainableDAO<CurrencyRate>{
    //todo.  в БД можно настроить создание ИД нормально(последовательно), а не так.
    // при перезагрузке класса он обнулится, я знаю, это все просто для демонстрации. если заменить
    // ИД на Integer, можно сделать налл в объекте и, теоритически, монго сгененирует ИД сам но тогда
    // у нас нет никакой гарантии в порядке чтения записаей и нужно менять либо ид, либо добавить
    // дату в класс, что значительно лучше.
    private static int ID_COUNTER =6;
    MongoCollection currencyRatesMongo = MongoClientSetUp.getMongoCollection(CurrencyRate.class);

    @Override
    public int create(CurrencyRate obj) throws SQLException {
        CurrencyRate o = obj;
        o.setId(ID_COUNTER);
         currencyRatesMongo.insertOne(o);
        ID_COUNTER++;

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

//    @Override
//    public int create(CurrencyRate obj) throws SQLException {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.save(obj);
//        tx1.commit();
//        session.close();
//        //todo check is works
//        return obj.getId();
//    }
//
//    @Override
//    public CurrencyRate read(int key) throws SQLException {
//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CurrencyRate.class, key);
//    }
//
//    //un-implemented method
//
//
//    //doesnt work in jdbc
//    //todo it doesnt work
//    public List<CurrencyRate> readListByBankId(int bankKey) throws SQLException {
//        String hql = "from CurrencyRate where bankid =:bankid2";
//        List<CurrencyRate> result = HibernateSessionFactoryUtil.getSessionFactory().openSession().
//                createQuery(hql).setParameter("bankid2", bankKey).list();
//        return result;
//    }
//
//    //todo check HQL
//    public CurrencyRate read(int bankKey, Currency currency) throws SQLException {
//        String hql = "from CurrencyRate  c where c.bankId =:bankId and c.currency =:currency";
//        CurrencyRate currencyRateForReturning = (CurrencyRate) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(hql)
//                .setParameter("bankId", bankKey)
//                .setParameter("currency", currency).stream().findAny().orElse(null);
//        return currencyRateForReturning;
//
//    }
//
//    @Override
//    public void update(CurrencyRate obj) throws SQLException {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        update(obj, session);
//        //todo ask. thread-safe?
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    @Override
//    public void update(CurrencyRate obj, Session s) throws SQLException {
//        Session session = s;
//        Transaction tx1 = session.beginTransaction();
//        session.update(obj);
//    }
//
//    @Override
//    public void delete(int key) throws SQLException {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.delete(key);
//        tx1.commit();
//        session.close();
//    }
//
//    @Override
//    public Session getSession() {
//        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
//    }

}
