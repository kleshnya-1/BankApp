package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.ConvertingValueException;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.sql.SQLException;

@Log4j2
public class CurrencyConverter {
    CurrencyRateService currencyRateService = new CurrencyRateService();

    public double returnConvertedAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        CurrencyRate currencySource;
        CurrencyRate currencyTarget;
        Double sourceAmountByn;
        Double targetAmount;

        if (!sourceAcc.getCurrency().equals(Currency.BYN)) {
            currencySource = currencyRateService.getLastCurrency(sourceAcc.getCurrency(), sourceAcc.getBank().getId());
            sourceAmountByn = amount * currencySource.getRateToByn();
        } else sourceAmountByn = amount;

        if (!targetAcc.getCurrency().equals(Currency.BYN)) {
            currencyTarget = currencyRateService.getLastCurrency(targetAcc.getCurrency(), targetAcc.getBank().getId());
            targetAmount = sourceAmountByn / currencyTarget.getRateToByn();
        } else targetAmount = sourceAmountByn;

        if (targetAmount==null||sourceAmountByn==null) throw new ConvertingValueException();
        log.info(amount + " " + sourceAcc.getCurrency() + " converted to " + targetAmount + " " + targetAcc.getCurrency());
        return targetAmount;
    }
}
