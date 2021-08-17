package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.TransferHistory;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    private Map<Class, IMaintainableDAO> factoryMap = new HashMap<>();

    public DaoFactory() {
        factoryMap.put(new Account().getClass(), new AccountDAOImpl());
        factoryMap.put(new Bank().getClass(), new BankDAOImpl());
        factoryMap.put(new Client().getClass(), new ClientDAOImpl());
        factoryMap.put(new TransferHistory().getClass(), new TransferHistoryDAOImpl());
    }

    public IMaintainableDAO createDaoFactory(Class daoClass) {
        return factoryMap.get(daoClass);
    }
}
