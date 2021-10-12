package ru.laptseu.bankapp.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Component
@RequiredArgsConstructor
public class CurrencyConverter {
    private final CurrencyRateService currencyRateService;

    public double convert(int sourceBankId, Currency sourceCurrency, int targetBankId, Currency targetCurrency, Double amount) {
        Double sourceRate = currencyRateService.read(sourceBankId, sourceCurrency);
        Double targetRate = currencyRateService.read(targetBankId, targetCurrency);
        return sourceRate * amount / targetRate;
    }
}
