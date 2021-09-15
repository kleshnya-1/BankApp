package ru.laptseu.bankapp.services;

import java.util.HashMap;
import java.util.Map;

// TODO: 14.09.2021 in progress. not for checking 
public class ServiceFactory {
    private static final Map<Class, IMaintainableService> factoryMap = new HashMap<>();

    static {
//        factoryMap.put(Account.class, new AccountService());
//        factoryMap.put(Bank.class, new BankService());
//        factoryMap.put(Client.class, new ClientService());
//        factoryMap.put(CurrencyRate.class, new CurrencyRateService());
//        factoryMap.put(TransferHistory.class, new TransferHistoryService());
    }

    public ServiceFactory() {
    }

    public static IMaintainableService get(Class clazz) {
        return factoryMap.get(clazz);
    }
}
