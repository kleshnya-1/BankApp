package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Currency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
//особенности чтения курса (нужно знать ИД банка и валюту, а не только ИД банка) не дают имплементировать
public class CurrencyRateService implements IMaintainableService<BankRateListDocument> {
    private final CurrRateDocumentsRepoInMongoExtends dao;

    public BankRateListDocument save(BankRateListDocument obj) {
       obj.setDate(Calendar.getInstance().getTime());
        return  dao.save(obj);
    }


    public BankRateListDocument read(int key)  {
      return dao.findFirstByBankIdOrderByDateDesc(key);

    }

    public Double read(int key, Currency c) {
        return read(key).getCurrenciesAndRates().get(c);
    }
}
