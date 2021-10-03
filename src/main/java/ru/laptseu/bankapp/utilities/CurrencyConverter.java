package ru.laptseu.bankapp.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Component
@RequiredArgsConstructor
public class CurrencyConverter {
    private final CurrencyRateService currencyRateService;

    public double convert(Bank sourceBank, Currency sourceCurrency, Bank targetBank, Currency targetCurrency, Double amount) {
        Double sourceRate = currencyRateService.read(sourceBank.getId(), sourceCurrency);
        Double targetRate = currencyRateService.read(targetBank.getId(), targetCurrency);
        return sourceRate * amount / targetRate;
    }
}
