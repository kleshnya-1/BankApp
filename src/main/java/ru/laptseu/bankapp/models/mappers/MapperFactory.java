package ru.laptseu.bankapp.models.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.Entity;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MapperFactory {
    //it doesn't work. autoconfigured beans not loaded by spring context
    private static final Map<Class<? extends Entity>, Class<? extends MapperInterface>> FACTORY_MAP = new HashMap<>();

    static {
        FACTORY_MAP.put(Account.class, AccountMapper.class);
        FACTORY_MAP.put(Bank.class, BankMapper.class);
        FACTORY_MAP.put(Client.class, ClientMapper.class);
    }

    private final ApplicationContext applicationContext;

    public MapperInterface get(Class clazz) {
        return applicationContext.getBean(FACTORY_MAP.get(clazz));
    }
}
