package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.repositories.CurrencyRateDocumentsRepository;

import java.util.Calendar;


@Service
@Getter
@RequiredArgsConstructor
public class CurrencyRateService implements IMaintainableService<BankRateListDocument> {
    private final CurrencyRateDocumentsRepository dao;

    public BankRateListDocument save(BankRateListDocument obj) {
        obj.setDate(Calendar.getInstance().getTime());
        return dao.save(obj);
    }

    public BankRateListDocument read(int key) {
        return dao.findFirstByBankIdOrderByDateDesc(key);
    }

    public Double read(int key, Currency c) {
        return read(key).getCurrenciesAndRates().get(c);
    }
}
