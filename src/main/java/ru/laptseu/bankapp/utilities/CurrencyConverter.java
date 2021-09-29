package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CurrencyConverter {
    public double convert(Double sourceRate, Double targetRate, Double amount) throws RuntimeException {
        return sourceRate * amount / targetRate;
    }
}
