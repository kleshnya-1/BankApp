package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.*;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static final Map<Class, IMaintainableDAO> factoryMap = new HashMap<>();

    static {
        factoryMap.put(Account.class, new AccountDAOImpl());
        factoryMap.put(Bank.class, new BankDAOImpl());
        factoryMap.put(Client.class, new ClientDAOImpl());
        factoryMap.put(CurrencyRate.class, new CurrencyRateDAO());
        factoryMap.put(TransferHistory.class, new TransferHistoryDAOImpl());
    }

    public DaoFactory() {
    }

    public static IMaintainableDAO get(Class clazz) {
        return factoryMap.get(clazz);
    }
}
