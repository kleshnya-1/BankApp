package ru.laptseu.bankapp.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.Main;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.repository.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class RepositoryFactory {

    //todo insert MAin when DB will work
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    private static final Map<Class, Class> factoryMap = new HashMap<>();


    static {
        factoryMap.put(Account.class, AccountRepo.class);
        factoryMap.put(Bank.class, BankRepo.class);
        factoryMap.put(Client.class, ClientRepo.class);
        factoryMap.put(CurrRateDocumentsRepoInMongoExtends.class, CurrRateDocumentsRepoInMongoExtends.class);
        factoryMap.put(TransferHistory.class, TransferHistoryRepository.class);

    }
    // TODO: 21.09.2021 check how it works
    public CrudRepository get(Class clazz) {
        return (CrudRepository) applicationContext.getBean(factoryMap.get(clazz));
    }
}
