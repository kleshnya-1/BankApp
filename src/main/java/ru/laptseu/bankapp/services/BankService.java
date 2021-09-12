package ru.laptseu.bankapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;

//todo in progress. this and rest of services is not for checking
@Service
public class BankService implements IMaintainableService<Bank> {

    IMaintainableDAO<Bank> bankDao;

    @Autowired
    public void setBankDao(IMaintainableDAO<Bank> bankDao) {
        this.bankDao = bankDao;
    }

    @Override
    public int persist(Bank obj) throws SQLException {
        int id = bankDao.save(obj);
        return id;
    }

    @Override
    public Bank read(int key) throws SQLException {
        return bankDao.read(key);
    }

    @Override
    public Bank read(Currency currency, int key) throws SQLException {
        throw new UnsupportedOperationException("Only for currencyRate");
    }

    @Override
    public void update(Bank bank) throws SQLException {
        bankDao.update(bank);
    }

    @Override
    public void delete(int key) throws SQLException {
        bankDao.delete(key);
    }
}
