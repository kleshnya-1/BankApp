package ru.laptseu.bankapp.pages;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {

    private static final Map<Class, IPages> factoryMap = new HashMap<>();

    static {
        factoryMap.put(BankPage.class, new BankPage());
        factoryMap.put(ClientPage.class, new ClientPage());

    }

    public PageFactory() {
    }

    public static IPages get(Class clazz) {
        return factoryMap.get(clazz);
    }


}
