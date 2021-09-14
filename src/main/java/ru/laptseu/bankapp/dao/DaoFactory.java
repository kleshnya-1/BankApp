package ru.laptseu.bankapp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class DaoFactory {

    private final AccountDAOImpl accountDAO;
    private final BankDAOImpl bankDAO;
    private final ClientDAOImpl clientDAO;
    private final CurrRateDocumentsDAO currRateDocumentsDAO;
    private final TransferHistoryDAOImpl transferHistoryDAO;


    private Map<Class, IMaintainableDAO> factoryMap = new HashMap<>();

    private void fillMap(){
        factoryMap.put(accountDAO.getClass(), accountDAO);
        factoryMap.put(bankDAO.getClass(), bankDAO);
        factoryMap.put(clientDAO.getClass(), clientDAO);
        factoryMap.put(currRateDocumentsDAO.getClass(), currRateDocumentsDAO);
        factoryMap.put(transferHistoryDAO.getClass(), transferHistoryDAO);
    }

    public IMaintainableDAO get(Class clazz) {
        fillMap();
        return factoryMap.get(clazz);
    }

/*
    //все по ТЗ. возвращает всегда нужный экземпляр и удобно добавлять новые. жаль, что не работает
private final AccountDAOImpl accountDAO;
    private final BankDAOImpl bankDAO;
    private final ClientDAOImpl clientDAO;
    private final MongoBankRateDAOImpl currencyRateDAO;
    private final TransferHistoryDAOImpl transferHistoryDAO;

  @Bean
    DaoFactory daoFactory() {
        return new DaoFactory(accountDAO, bankDAO, clientDAO, currencyRateDAO, transferHistoryDAO);
    }
     //а это конструктор
     public DaoFactory(IMaintainableDAO... iMaintainableDAOS) {
        Arrays.stream(iMaintainableDAOS).forEach(iMaintainableDAO -> {
            factoryMap.put(iMaintainableDAO.getEntity().getClass(), iMaintainableDAO);
        });
    }
*/

}
