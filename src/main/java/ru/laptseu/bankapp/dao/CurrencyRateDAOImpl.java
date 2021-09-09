package ru.laptseu.bankapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.CustomDocument;
import ru.laptseu.bankapp.utilities.MongoClientFactoryAndSetUp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

//todo круцти спринг дату
@Log4j2
@Component
public class CurrencyRateDAOImpl //implements  IMaintainableDAO<CurrencyRate>
{

    @Autowired
    MongoCollection currencyRatesMongoCollection;// = MongoClientFactoryAndSetUp.getMongoCollection("CurrencyRates", CustomDocument.class);

    public CurrencyRateDAOImpl() {
    }

    //@Override
    public int save(CurrencyRate obj) throws SQLException {
        obj.setBankId(obj.getBank().getId());
        //manual @Transient
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
//todo close session and fix return
        return 0;
    }

    public int save(List<CurrencyRate> list) throws SQLException {
        //todo stream and return
        for (CurrencyRate cr : list) {
            save(cr);
        }
        return 1;
    }

    public List<CurrencyRate> read(int key) {
        //todo null exc
        CustomDocument o3 = (CustomDocument) currencyRatesMongoCollection.find(eq("bankId", key)).first();
        if (o3 == null) {
            List<CurrencyRate> empty = new ArrayList<>();
            return empty;
        } else {
            return o3.getCurrencies();
        }

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

    }

    public void update(CurrencyRate obj, Session conn) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Session getSession() {
        throw new UnsupportedOperationException();
    }

    public CurrencyRate getLastCurrency(Currency curr, int bankId) {
        currencyRatesMongoCollection = MongoClientFactoryAndSetUp.getMongoCollection(bankId, CurrencyRate.class);
        return (CurrencyRate) currencyRatesMongoCollection.find(Filters.eq("currency", curr.toString())).first();
    }
}
