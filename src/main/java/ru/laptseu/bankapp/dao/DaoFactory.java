package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.TransferHistory;

import java.util.HashMap;
import java.util.Map;

@Log4j2
//@Component
//todo remove it? spring replaced it
public class DaoFactory {
    private static final Map<Class, IMaintainableDAO> factoryMap = new HashMap<>();

    static {
       // factoryMap.put(Account.class, new AccountDAOImpl());
     //   factoryMap.put(Bank.class, new BankDAOImpl());
        //factoryMap.put(Client.class, new ClientDAOImpl());
        // factoryMap.put(CurrencyRate.class, new CurrencyRateDAOImpl());//new DB
        //factoryMap.put(TransferHistory.class, new TransferHistoryDAOImpl());
    }

    public static IMaintainableDAO get(Class clazz) {
        return factoryMap.get(clazz);
    }
}
