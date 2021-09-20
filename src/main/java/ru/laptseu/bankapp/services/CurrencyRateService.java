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
import java.util.Comparator;
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

    private BankRateListDocument findOrCreateDocument(int bankId) throws Throwable {
        //todo ask. мне кажется, есть смысл заменить на Integer.
        if (bankId == 0)
            throw new NullPointerException("Can't save CurrencyRate without bank ID");
        BankRateListDocument document = null;
        try {
            document = dao.read(bankId);
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
        if (list.isEmpty()) {
            throw new NullPointerException("list is empty");
        }
        //в данном случае мы не можем быть 100% уверены в том, что все курсы в листе будут от одного банка. по этой причине такая реализация.
        int firstBankId = list.get(0).getBankId();
        boolean singleBankList = true;
        for (CurrencyRate cr : list) {
            if (cr.getBankId() != firstBankId) {
                singleBankList = false;
                break;
            }
        }
        if (singleBankList) {
            BankRateListDocument bankRateListDocument = findOrCreateDocument(firstBankId);
            bankRateListDocument.getCurrencies().addAll(list);
            dao.save(bankRateListDocument);
        } else {
            list.forEach(this::save);
        }
    }

    public List<CurrencyRate> read(int key) throws Throwable {
        List<CurrencyRate> rates = null;
        BankRateListDocument bankRateListDocument;
        try {
            bankRateListDocument = dao.read(key);
        } catch (EntityNotFoundException e) {
            log.debug(e);
            return rates;
        }
        rates = bankRateListDocument.getCurrencies();
        return rates;
    }

    public CurrencyRate read(int key, Currency c) {
        List<CurrencyRate> singleBankCurrencies = read(key);
        if (singleBankCurrencies == null) {
            return null;
        }
        singleBankCurrencies.stream().filter(cr -> cr.getCurrency().equals(c));
        if (singleBankCurrencies.isEmpty()) {
            return null;
            // TODO: 16.09.2021 test bad cases
        }
        singleBankCurrencies.sort(Comparator.comparing(CurrencyRate::getDate));
        return singleBankCurrencies.get(singleBankCurrencies.size() - 1);
    }
}
