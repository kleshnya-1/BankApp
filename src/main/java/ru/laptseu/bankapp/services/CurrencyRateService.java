package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRateBankList;
import ru.laptseu.bankapp.models.dto.EntityDto;

import java.util.Calendar;
import java.util.List;


@Service
@Getter
@RequiredArgsConstructor
public class CurrencyRateService extends AbstractService {
    private final Class resourceEntityClass = CurrencyRateBankList.class;


    public CurrencyRateBankList save(CurrencyRateBankList obj) {
        obj.setDate(Calendar.getInstance().getTime());
        return (CurrencyRateBankList) getRepository().save(obj);
    }

    public CurrencyRateBankList read(int key) {
        return (CurrencyRateBankList) getRepository().findById(key).orElse(null);
    }

    public Double read(int key, Currency c) {
        return read(key).getCurrenciesAndRates().get(c);
    }

    public CurrencyRateBankList getDtoList(int id) {
        return read(id);
    }

    @Override
    List readDto() {
        throw new UnsupportedOperationException();
    }

    @Override
    EntityDto readDto(int id) {
        throw new UnsupportedOperationException();
    }
}
