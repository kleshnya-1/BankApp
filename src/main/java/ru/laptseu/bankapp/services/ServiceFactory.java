package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.models.*;

import java.util.HashMap;
import java.util.Map;

//remove it?
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
