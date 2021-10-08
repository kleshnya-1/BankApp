package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.BankRateList;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.repositories.RepositoryFactory;

import java.util.Calendar;
import java.util.Optional;


@Service
@Getter
@RequiredArgsConstructor
public class CurrencyRateService implements IMaintainableService<BankRateList> {
    private final Account entity;
    private final RepositoryFactory factory;
    private final CrudRepository dao = factory.get(entity.getClass());

    public BankRateList save(BankRateList obj) {
        obj.setDate(Calendar.getInstance().getTime());
        return (BankRateList) dao.save(obj);
    }

    public BankRateList read(int key) {
        return (BankRateList) dao.findById(key).orElse(null);
    }

    public Double read(int key, Currency c) {
        return read(key).getCurrenciesAndRates().get(c);
    }
}
