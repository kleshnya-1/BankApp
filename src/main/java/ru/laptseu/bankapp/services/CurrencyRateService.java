package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.BankRateList;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.mappers.MapperFactory;
import ru.laptseu.bankapp.repositories.RepositoryFactory;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Getter
@RequiredArgsConstructor
public class CurrencyRateService implements IMaintainableService {
    private final Account entity;
    private final RepositoryFactory repositoryFactory;
    private final MapperFactory mapperFactory;
    private final ServiceFactory serviceFactory;

    public BankRateList save(BankRateList obj) {
        obj.setDate(Calendar.getInstance().getTime());
        return (BankRateList) getDao().save(obj);
    }

    public BankRateList read(int key) {
        return (BankRateList) getDao().findById(key).orElse(null);
    }

    public Double read(int key, Currency c) {
        return read(key).getCurrenciesAndRates().get(c);
    }

    public BankRateList getDtoList(int id) {
        return read(id);
    }
}
