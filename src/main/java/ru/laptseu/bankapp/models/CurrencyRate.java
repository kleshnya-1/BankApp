package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
public class CurrencyRate extends EntityModel{
     private Bank bank;
    private int bankId;
    private Currency currency;
    private double rateToByn;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CurrencyRate)) {
            return false;
        }
        CurrencyRate c = (CurrencyRate) o;
        //todo explain если банк был просто присвоен, ИД будет == 0.
        // а вот если курс прочтен из базы, будет только ИД, а банк null. иначе цикличный вызов
        if (bank != null && c.getBank() != null) {
            return currency.equals(c.getCurrency()) &&
                    bank.equals(c.getBank()) &&
                    rateToByn == c.getRateToByn();
        }
        return currency.equals(c.getCurrency()) &&
                bankId == c.getBankId() &&
                rateToByn == c.getRateToByn();
    }
}
