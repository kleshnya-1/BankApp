package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Getter
@Setter
//@Entity тут нет. его в дао банка дао курса подставляет
//todo in progress.
//todo ask should it have any ID?
public class CurrencyRate extends EntityModel {

    Bank bank;
    int bankId;
    private Currency currency;
    private double rateToByn;

    public CurrencyRate(Currency currency, double rateToByn) {
        this.currency = currency;
        this.rateToByn = rateToByn;
    }

    public CurrencyRate() {
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
        if (bank != null || c.getBank() != null) {
            return currency.equals(c.getCurrency()) &&
                    bank.equals(c.getBank()) &&
                    rateToByn == c.getRateToByn();
        }
        return currency.equals(c.getCurrency()) &&
                rateToByn == c.getRateToByn();
    }
}
