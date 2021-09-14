package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;

//todo in progress. this and rest of services is not for checking
@Service
@RequiredArgsConstructor
public class BankService implements IMaintainableService<Bank> {


    private final BankDAOImpl bankDao;


    @Override
    public int save(Bank obj) throws SQLException {
        int id = bankDao.save(obj).getId();
        return id;
    }

    @Override
    public Bank read(int key) throws Throwable {
        return bankDao.read(key);
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
