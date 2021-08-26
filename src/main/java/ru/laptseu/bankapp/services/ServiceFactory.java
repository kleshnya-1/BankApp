package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final Map<Class, IMaintainableService> factoryMap = new HashMap<>();

    static {
        factoryMap.put(Account.class, new AccountService());
        factoryMap.put(Bank.class, new BankService());
        factoryMap.put(Client.class, new ClientService());
        factoryMap.put(CurrencyRate.class, new CurrencyRateService());
    }

    public ServiceFactory() {
    }

    public static IMaintainableService get(Class clazz) {
        return factoryMap.get(clazz);
    }
}
