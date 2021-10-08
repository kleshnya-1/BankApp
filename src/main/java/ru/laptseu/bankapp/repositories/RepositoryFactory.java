package ru.laptseu.bankapp.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RepositoryFactory {
    private static final Map<Class<? extends Entity>, Class<? extends CrudRepository>> FACTORY_MAP = new HashMap<>();

    static {
        FACTORY_MAP.put(Account.class, AccountRepository.class);
        FACTORY_MAP.put(Bank.class, BankRepository.class);
        FACTORY_MAP.put(Client.class, ClientRepository.class);
        FACTORY_MAP.put(BankRateList.class, CurrencyRateRepository.class);
        FACTORY_MAP.put(TransferHistory.class, TransferHistoryRepository.class);
    }

    private final ApplicationContext applicationContext;

    public CrudRepository get(Class clazz) {
        return applicationContext.getBean(FACTORY_MAP.get(clazz));
    }
}
