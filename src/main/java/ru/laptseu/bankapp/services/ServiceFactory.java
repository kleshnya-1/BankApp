package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ServiceFactory {
    private static final Map<Class<? extends Entity>, Class<? extends IMaintainableService>> FACTORY_MAP = new HashMap<>();

    static {
        FACTORY_MAP.put(Account.class, AccountService.class);
        FACTORY_MAP.put(Bank.class, BankService.class);
        FACTORY_MAP.put(Client.class, ClientService.class);
        FACTORY_MAP.put(BankRateListDocument.class, CurrencyRateService.class);
        FACTORY_MAP.put(TransferHistory.class, TransferHistoryService.class);
    }

    private final ApplicationContext applicationContext;

    public IMaintainableService get(Class clazz) {
        return applicationContext.getBean(FACTORY_MAP.get(clazz));
    }
}