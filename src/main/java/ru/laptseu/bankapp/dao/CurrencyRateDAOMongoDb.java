package ru.laptseu.bankapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.hibernate.Session;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.MongoClientFactoryAndSetUp;

import java.sql.SQLException;

import static com.mongodb.client.model.Filters.eq;

public class CurrencyRateDAOMongoDb implements IMaintainableDAO<CurrencyRate> {
    //todo.  в БД можно настроить создание ИД нормально(последовательно), а не так.
    // при перезагрузке класса он обнулится, я знаю, это все просто для демонстрации. если заменить
    // ИД на Integer, можно сделать налл в объекте и, теоритически, монго сгененирует ИД сам но тогда
    // у нас нет никакой гарантии в порядке чтения записаей и нужно менять либо ид, либо добавить
    // дату в класс, что значительно лучше.

    MongoCollection currencyRatesMongo;

    @Override
    public int create(CurrencyRate obj) throws SQLException {
        currencyRatesMongo = MongoClientFactoryAndSetUp.getMongoCollection(obj.getBankId(), CurrencyRate.class);
        currencyRatesMongo.insertOne(obj);
        //todo ask есть проблема: монго созает ИД как строку с буквами. у нас инт. все дао менять придется.
        // в этом случае сейчас это ДОЭ имеет void метод
        return -1;//obj.getId();
    }

    @Override
    public CurrencyRate read(int key) throws SQLException {
        return (CurrencyRate) currencyRatesMongo.find(eq("_id", key)).first();
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

    public CurrencyRate getLastCurrency(Currency curr, int bankId) {
        currencyRatesMongo = MongoClientFactoryAndSetUp.getMongoCollection(bankId, CurrencyRate.class);
        return (CurrencyRate) currencyRatesMongo.find(Filters.eq("currency", curr.toString())).first();
    }
}
