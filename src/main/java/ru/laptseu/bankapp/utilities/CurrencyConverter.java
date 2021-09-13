package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Log4j2
@Component
public class CurrencyConverter {
       CurrencyRateService currencyRateService;
    @Autowired
    public CurrencyConverter(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

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
