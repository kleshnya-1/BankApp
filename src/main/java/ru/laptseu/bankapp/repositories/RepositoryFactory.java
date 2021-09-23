package ru.laptseu.bankapp.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class RepositoryFactory {
    static {
        factoryMap.put(Account.class, AccountRepo.class);
        factoryMap.put(Bank.class, BankRepo.class);
        factoryMap.put(Client.class, ClientRepo.class);
        factoryMap.put(BankRateListDocument.class, CurrRateDocumentsRepoInMongoExtends.class);
        factoryMap.put(TransferHistory.class, TransferHistoryRepository.class);

    }
    private static final Map<Class, Class> factoryMap = new HashMap<>();

    private final ApplicationContext applicationContext;

    public CrudRepository get(Class clazz) {
        return (CrudRepository) applicationContext.getBean(factoryMap.get(clazz));
    }
}
