package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.models.Bank;

@Log4j2
@Service
@RequiredArgsConstructor
public class BankService implements IMaintainableService<Bank> {
    private final BankDAOImpl bankDao;

    @Override
    public int save(Bank obj) {
        int id = bankDao.save(obj).getId();
        return id;
    }

    @Override
    public Bank read(int key) throws Throwable {
            return bankDao.read(key);
    }

    @Override
    public void update(Bank bank) {
        bankDao.update(bank);
    }

    @Override
    public void delete(int key) {
        bankDao.delete(key);
    }
}
