package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.CurrRateDocumentsDAO;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
//особенности чтения курса (нужно знать ИД банка и валюту, а не только ИД банка) не дают имплементировать
public class CurrencyRateService {
    private final CurrRateDocumentsDAO dao;

    //объект курса конвертируется в документ и сохраняется
    public void save(CurrencyRate obj) {
        BankRateListDocument bankRateListDocument = findOrCreateDocument(obj.getBankId());
        bankRateListDocument.getCurrencies().add(obj);
        dao.save(bankRateListDocument);
    }

    private BankRateListDocument findOrCreateDocument(int bankId) {
        //todo ask. мне кажется, есть смысл заменить на Integer.
        if (bankId == 0)
            throw new NullPointerException("Can't save CurrencyRate without bank ID");
        BankRateListDocument document = null;
        try {
            document = dao.readByBankId(bankId);
        } catch (EntityNotFoundException e) {
            log.debug("bankRateListDocument with ID " + bankId + " not found");
        }
        if (document == null) {
            document = new BankRateListDocument();
            List<CurrencyRate> list = new ArrayList<>();
            document.setCurrencies(list);
            document.setBankId(bankId);
        }
        return document;
    }

    public void save(List<CurrencyRate> list) {
        if (list.size() == 0) {
            throw new NullPointerException("list is empty");
        }
        //в данном случае мы не можем быть 100% уверены в том, что все курсы в листе будут от одного банка. по этой причине такая реализация.
        int firstBankId = list.get(0).getBankId();
        boolean singleBankList = true;

        while (singleBankList) {
            for (CurrencyRate cr : list) {
                if (cr.getBankId() != firstBankId) {
                    singleBankList = false;
                }
            }
        }

        if (singleBankList) {
            BankRateListDocument bankRateListDocument = findOrCreateDocument(firstBankId);
            bankRateListDocument.getCurrencies().addAll(list);
            dao.save(bankRateListDocument);
        } else {
            Collections.reverse(list); //для соблюдения LIFO в коллекции сохраняем по одному
            list.stream().forEach(currencyRate -> {
                save(currencyRate);
            });
        }
    }

    public List<CurrencyRate> read(int key) {
        List<CurrencyRate> rates = new ArrayList<>();
        BankRateListDocument bankRateListDocument;
        try {
            bankRateListDocument = dao.readByBankId(key);
        } catch (EntityNotFoundException e) {
            log.debug(e);
            return rates;
        }

        if (bankRateListDocument.getCurrencies() != null) {
            rates = bankRateListDocument.getCurrencies();
            rates.stream().forEach(currencyRate -> currencyRate.setBankId(key));
        }
        return rates;
    }

    public CurrencyRate read(int key, Currency c) {
        List<CurrencyRate> reversed = read(key);
        Collections.reverse(reversed);
        return reversed.stream().filter(cr -> cr.getCurrency().equals(c)).findFirst().orElse(null);
    }

}
