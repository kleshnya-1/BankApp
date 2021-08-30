package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.CurrencyRateDAOMongoDb;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

public class CurrencyRateService implements IMaintainableService<CurrencyRate> {
    //todo или мне добавить его в интерфейс и заглушить в других сервисах/дао?
    // getLastCurrency() is specific method.
    CurrencyRateDAOMongoDb currencyRateDao = new CurrencyRateDAOMongoDb();


    @Override
    public CurrencyRate create(String[] paramArr) throws SQLException {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setBankId(Integer.parseInt(paramArr[0]));
        currencyRate.setCurrency(Currency.valueOf(paramArr[1]));
        currencyRate.setRateToByn(Integer.parseInt(paramArr[2]));
        return currencyRate;
    }

    @Override
    public int persist(CurrencyRate obj) throws SQLException {
        int id = currencyRateDao.create(obj);
        return id;
    }

    @Override
    public CurrencyRate read(int key) throws SQLException {
        return currencyRateDao.read(key);
    }

    @Override
    public void update(String[] paramArr) throws SQLException {
        CurrencyRate cr;
        cr = create(paramArr);
        //cr.setId(Integer.parseInt(paramArr[paramArr.length]));
        currencyRateDao.update(cr);
    }

    @Override
    public void update(CurrencyRate currencyRate) throws SQLException {
        currencyRateDao.update(currencyRate);
    }

    @Override
    public void delete(int key) throws SQLException {
    }

    public CurrencyRate getLastCurrency(Currency curr, int bankId) {
        return currencyRateDao.getLastCurrency(curr, bankId);
    }
}
