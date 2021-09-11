package ru.laptseu.bankapp.services;

import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

@Service
//todo in progress. not for checking
public class CurrencyRateService implements IMaintainableService<CurrencyRate> {

    //todo  getLastCurrency() ref
    CurrencyRateDAOImpl currencyRateDao = new CurrencyRateDAOImpl();

    public int persist(CurrencyRate obj) throws SQLException {
        int id = currencyRateDao.save(obj);
        return id;
    }

    //todo fix

    //key - bankId
    public CurrencyRate read(int key) throws SQLException {
        throw new UnsupportedOperationException("no reason for reading rate without choosing currency");
    }

    public CurrencyRate read(Currency currency, int key) throws SQLException {
        return currencyRateDao.read(key, currency);
    }

    public void update(CurrencyRate currencyRate) throws SQLException {
        currencyRateDao.update(currencyRate);
    }

    public void delete(int key) throws SQLException {
    }

    public CurrencyRate getLastCurrency(Currency curr, int bankId) {
        return currencyRateDao.getLastCurrency(curr, bankId);
    }
}
