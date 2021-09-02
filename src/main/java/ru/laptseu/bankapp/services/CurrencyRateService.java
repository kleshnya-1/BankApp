package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

//todo in progress. road to NoSQL
public class CurrencyRateService implements IMaintainableService<CurrencyRate> {

    //todo  getLastCurrency() ref
    CurrencyRateDAOImpl currencyRateDao = new CurrencyRateDAOImpl();

    @Override
    public CurrencyRate create(String[] paramArr) throws SQLException {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.getBank().setId(Integer.parseInt(paramArr[0]));
        currencyRate.setCurrency(Currency.valueOf(paramArr[1]));
        currencyRate.setRateToByn(Integer.parseInt(paramArr[2]));
        return currencyRate;
    }

    @Override
    public int persist(CurrencyRate obj) throws SQLException {
        int id = currencyRateDao.save(obj);
        return id;
    }

    @Override
    //key - bankId
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
