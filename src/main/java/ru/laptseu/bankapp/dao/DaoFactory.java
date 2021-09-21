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
    private final Map<Class, IMaintainableDAO> factoryMap = new HashMap<>();

    static  {
        factoryMap.put(accountDAO.getClass(), accountDAO);
        factoryMap.put(bankDAO.getClass(), bankDAO);
        factoryMap.put(clientDAO.getClass(), clientDAO);
        factoryMap.put(currRateDocumentsDAO.getClass(), currRateDocumentsDAO);
        factoryMap.put(transferHistoryDAO.getClass(), transferHistoryDAO);
    }

//    public IMaintainableDAO get(Class clazz) {
//        fillMap();
//        return factoryMap.get(clazz);
//    }
}
