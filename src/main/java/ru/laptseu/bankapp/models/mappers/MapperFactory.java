package ru.laptseu.bankapp.models.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.*;
import ru.laptseu.bankapp.repositories.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MapperFactory {
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
