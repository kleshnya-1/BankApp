package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Log4j2
@Component
public class CurrencyConverter {
    @Autowired
    CurrencyRateService currencyRateService;// = new CurrencyRateService();

    public double convert(Double sourceRate, Double targetRate, Double amount) {
        if (sourceRate == null || targetRate == null || amount == null) {
            log.error("source: " + sourceRate + " target: " + targetRate + " returned 0");
            return 0;
        }
        return sourceRate * amount / targetRate;
    }
}
