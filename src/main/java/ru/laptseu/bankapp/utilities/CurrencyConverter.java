package ru.laptseu.bankapp.utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Log4j2
@Component
@RequiredArgsConstructor
public class CurrencyConverter {
    private final    CurrencyRateService currencyRateService;

    public double convert(Double sourceRate, Double targetRate, Double amount) throws RuntimeException {
        if (sourceRate == null || targetRate == null || amount == null) {
            RuntimeException e = new NullPointerException("source: " + sourceRate + " target: " + targetRate + " amount: "+amount);
            log.error(e);
            // TODO: 13.09.2021 throw exc
            throw e;
        }
        return sourceRate * amount / targetRate;
    }
}
