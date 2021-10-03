package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.repositories.AccountRepository;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class AccountService implements IMaintainableService<Account> {
    private final CommissionCalculator commissionCalculator;
    private final AccountRepository dao;
    private final TransferHistoryService transferHistoryService;
    private final CurrencyConverter currencyConverter;
    private final CurrencyRateService currencyRateService;

    public Account read(String num) {
        return dao.findById(num);
    }

    public int transferAmount(Account sourceAcc, Account targetAcc, double amount) {
        double commission = 0;
        double totalAmount = amount;

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
            totalAmount = currencyConverter.convert(sourceAcc.getBank(), sourceAcc.getCurrency(),
                    targetAcc.getBank(), targetAcc.getCurrency(), amount);
        }
        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);
        saveAccountsThroughTransaction(sourceAcc, targetAcc);
        // TODO: 15.09.2021 check bad cases
        return transferHistoryService.save(sourceAcc, targetAcc, amount);
    }

    @Transactional(rollbackFor = Exception.class)
    //must be overridable. я хотел сделать приватным
    public void saveAccountsThroughTransaction(Account sourceAcc, Account targetAcc) {
        dao.save(sourceAcc);
        dao.save(targetAcc);
        log.debug("Transaction from " + sourceAcc.getAccNumber() + " to " + targetAcc.getAccNumber() + " finished");
    }
}


