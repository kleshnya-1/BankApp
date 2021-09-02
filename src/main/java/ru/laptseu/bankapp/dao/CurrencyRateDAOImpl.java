package ru.laptseu.bankapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.hibernate.Session;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.MongoClientFactoryAndSetUp;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;

import static com.mongodb.client.model.Filters.eq;


public class CurrencyRateDAOImpl implements IMaintainableDAO<CurrencyRate> {
    //todo.  in progress. mongo

    MongoCollection currencyRatesMongo;

    //@Override
    public int save(CurrencyRate obj) throws SQLException {
        //Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();

        //todo check is works
        return obj.getId();
    }

    public void persistTestData(EntityManagerFactory entityManagerFactory, CurrencyRate editor)
            throws Exception {
//        TransactionManager transactionManager =
//                com.arjuna.ats.jta.TransactionManager.transactionManager();
//        transactionManager.begin();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.persist(editor);
//        entityManager.close();
//        transactionManager.commit();
    }

    @Override
    public CurrencyRate read(int key) {
        return (CurrencyRate) currencyRatesMongo.find(eq("_id", key)).first();
    }

    @Override
    public void update(CurrencyRate obj) throws SQLException {

    }

    @Override
    public void delete(int key) {

    }

    @Override
    public void update(CurrencyRate obj, Session conn) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Session getSession() {
        throw new UnsupportedOperationException();
    }

    public CurrencyRate getLastCurrency(Currency curr, int bankId) {
        currencyRatesMongo = MongoClientFactoryAndSetUp.getMongoCollection(bankId, CurrencyRate.class);
        return (CurrencyRate) currencyRatesMongo.find(Filters.eq("currency", curr.toString())).first();
    }
}
