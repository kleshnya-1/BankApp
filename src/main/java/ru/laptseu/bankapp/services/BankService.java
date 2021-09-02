package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Bank;

import java.sql.SQLException;

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
        int id = bankDao.create(obj);
        return id;
    }

    @Override
    public Bank read(int key) throws SQLException {
        return bankDao.read(key);
    }

    @Override
    public void update(String[] paramArr) throws SQLException {
        Bank bank;
        bank = create(paramArr);
        bank.setId(Integer.parseInt(paramArr[paramArr.length]));
        bankDao.update(bank);
    }

    @Override
    public void delete(int key) throws SQLException {
        bankDao.delete(key);
    }
}
