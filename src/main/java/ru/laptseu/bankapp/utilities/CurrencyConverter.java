package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.CurrencyRateDAO;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

@Log4j2
public class CurrencyConverter {
    private final BankDAOImpl bankDaoImpl = new BankDAOImpl();
    private final CurrencyRateDAO currencyRateDAO = new CurrencyRateDAO();

    public double returnConvertedAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        Bank sourceBank = bankDaoImpl.read(sourceAcc.getBankId());
        Bank targetBank = bankDaoImpl.read(targetAcc.getBankId());
        CurrencyRate currencySource;
        CurrencyRate currencyTarget;
        double sourceAmountByn;
        double targetAmount;

        if (!sourceAcc.getCurrency().equals(Currency.BYN)) {
            currencySource = currencyRateDAO.readList(sourceBank.getId()).stream().filter(c -> c.getCurrency().equals(sourceAcc.getCurrency())).findAny().orElse(null);
            sourceAmountByn = amount * currencySource.getRateToByn();
        } else sourceAmountByn = amount;

        if (!targetAcc.getCurrency().equals(Currency.BYN)) {
            currencyTarget = currencyRateDAO.readList(targetBank.getId()).stream().filter(c -> c.getCurrency().equals(targetAcc.getCurrency())).findAny().orElse(null);
            targetAmount = sourceAmountByn / currencyTarget.getRateToByn();
        } else targetAmount = sourceAmountByn;

        log.info(amount +" "+sourceAcc.getCurrency()+" converted to "+ targetAmount+" "+ targetAcc.getCurrency());
        return targetAmount;
    }

}
