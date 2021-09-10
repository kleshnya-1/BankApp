package ru.laptseu.bankapp.services;

import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;


//todo in progress. not for checking
@Service
public class BankService implements IMaintainableService<Bank> {

    IMaintainableDAO<Bank> bankDao = DaoFactory.get(Bank.class);

    @Override
    public Bank create(String[] paramArr) {
        Bank bank = new Bank();
        bank.setName(paramArr[0]);
        bank.setTransferFeeInPercent(Double.parseDouble(paramArr[1]));
        bank.setTransferFeeInPercentForNotNaturalPersons(Double.parseDouble(paramArr[2]));
        return bank;
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
        throw  new  UnsupportedOperationException("Only for currencyRate");
    }

    @Override
    public void update(String[] paramArr) throws SQLException {
        Bank bank;
        bank = create(paramArr);
        bank.setId(Integer.parseInt(paramArr[paramArr.length]));
        bankDao.update(bank);
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
