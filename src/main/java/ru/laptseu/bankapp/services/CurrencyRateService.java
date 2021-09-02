package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

public class CurrencyRateService implements IMaintainableService<CurrencyRate> {
    IMaintainableDAO<CurrencyRate> currencyRateDao = DaoFactory.get(CurrencyRate.class);

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
        return null;
    }

    @Override
    public void update(String[] paramArr) throws SQLException {

    }

    @Override
    public void delete(int key) throws SQLException {

    }
}
