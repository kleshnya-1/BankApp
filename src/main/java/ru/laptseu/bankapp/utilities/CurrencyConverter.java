package ru.laptseu.bankapp.utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CurrencyConverter {

    public double convert(Double sourceRate, Double targetRate, Double amount) throws RuntimeException {
        if (sourceRate == null || targetRate == null || amount == null) {
            RuntimeException e = new NullPointerException("source: " + sourceRate + " target: " + targetRate + " amount: " + amount);
            log.error(e);
            throw e;
        }
        return sourceRate * amount / targetRate;
    }
}
