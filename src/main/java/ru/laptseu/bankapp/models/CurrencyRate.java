package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
@Getter
@Setter
public class CurrencyRate {
    private Date date;
    private int bankId;
    private Currency currency;
    private double rateToByn;

    public CurrencyRate() {
        this.date = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CurrencyRate)) {
            return false;
        }
        CurrencyRate c = (CurrencyRate) o;

        return currency.equals(c.getCurrency()) &&
                bankId == c.getBankId() &&
                rateToByn == c.getRateToByn();
    }
}
