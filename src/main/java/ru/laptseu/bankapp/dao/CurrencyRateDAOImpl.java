package ru.laptseu.bankapp.dao;

import com.mongodb.client.MongoCollection;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.CustomDocument;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

//todo проблема с имплиментированием. метод read(int)
// потом тут появится спринг дата
@Log4j2
@Repository
public class CurrencyRateDAOImpl// implements  IMaintainableDAO<CurrencyRate>
{
    MongoCollection currencyRatesMongoCollection;

    @Autowired
    public CurrencyRateDAOImpl(MongoCollection currencyRatesMongoCollection) {
        this.currencyRatesMongoCollection = currencyRatesMongoCollection;
    }

    public SessionFactory getSessionFactory() {
        throw new UnsupportedOperationException("No sessionFactory in Mongo DB");
    }

    public int save(CurrencyRate obj) throws SQLException {
        obj.setBankId(obj.getBank().getId());
        Bank savedBank = obj.getBank();
        //todo ask manual @Transient иначе цикличная зависимость
        obj.setBank(null);
        //todo ask set bankId to null?

        CustomDocument document = (CustomDocument) currencyRatesMongoCollection.
                find(eq("bankId", obj.getBankId())).first();
        if (document == null) {
            document = new CustomDocument();
            List<CurrencyRate> list = new ArrayList<>();
            list.add(obj);
            document.setCurrencies(list);
            document.setBankId(obj.getBankId());
            currencyRatesMongoCollection.insertOne(document);
        } else {
            document.getCurrencies().add(obj);
            //todo ask replaceOne()?
            currencyRatesMongoCollection.replaceOne(eq("bankId", obj.getBankId()), document);
        }
        //todo ask как-то так. так сущность сохранится без него, но оставнийся объект может нормально функционировать
        obj.setBank(savedBank);
        //todo close session and fix return
        return 1;
    }

    public int save(List<CurrencyRate> list) throws SQLException {
        //todo stream and return
        for (CurrencyRate cr : list) {
            save(cr);
        }
        return 1;
    }

    //todo в интерфейсе возврящается один обънет. а у меня лист. в связи с
    // этим либо в остальные классы заглушку, либо не имплиментировать. а как лучше?
    // (сюда я даю ИД банка. и получаю лист его курсов. интерфейс не понимает,
    // так как он привык получать ИД сущности и ее возвращать (дженерик))
    public List<CurrencyRate> read(int key) {
        // Bank bankOwner = bankDAO.read(key);
        //todo null exc
        MongoCollection aa = currencyRatesMongoCollection;
        System.out.println(aa.toString());
        CustomDocument o3 = (CustomDocument) currencyRatesMongoCollection.find(eq("bankId", key)).first();
        //todo check with null
        List<CurrencyRate> rates;
        System.out.println(o3);
        if (o3 != null && o3.getCurrencies() != null) {
            rates = o3.getCurrencies();
            rates.stream().forEach(currencyRate -> currencyRate.setBankId(key));
        } else rates = new ArrayList<>();
        return rates;
    }

    public CurrencyRate read(int key, Currency c) {
        List<CurrencyRate> reversed = read(key);
        Collections.reverse(reversed);
        return reversed.stream().filter(cr -> cr.getCurrency().equals(c)).findFirst().orElse(null);
    }

    public void update(CurrencyRate obj) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void delete(int key) {
        throw new UnsupportedOperationException();
    }

    public void delete(CurrencyRate key) {
        throw new UnsupportedOperationException();
    }

    public void update(CurrencyRate obj, Session conn) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
