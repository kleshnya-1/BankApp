package ru.laptseu.bankapp.utilities;

import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {
    public double convert(Double sourceRate, Double targetRate, Double amount) throws RuntimeException {
        return sourceRate * amount / targetRate;
    }
}
