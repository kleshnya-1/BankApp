package ru.laptseu.bankapp.pages;

import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {

    private static final Map<Class, IPages> factoryMap = new HashMap<>();

    static {
        factoryMap.put(Bank.class, new BankPage());
        factoryMap.put(Client.class, new ClientPage());

    }

    public PageFactory() {
    }

    public static IPages get(Class clazz) {
        return factoryMap.get(clazz);
    }

}
