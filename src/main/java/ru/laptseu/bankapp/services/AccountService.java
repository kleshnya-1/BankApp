package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.sql.SQLException;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class AccountService implements IMaintainableService<Account> {
    private final AccountDAOImpl dao;
    private final CommissionCalculator commissionCalculator;
    private final TransferHistoryService transferHistoryService;
    private final CurrencyConverter currencyConverter;
    private final CurrencyRateService currencyRateService;

    //todo ask. тут вопрос по read() запрашиваем пока по Ид. а надо по ИД или уже по номеру аккаунта?
    // мы из сервисов понятия же не имеем, какой ИД у базы там.
    public int transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        double commission = 0;
        double totalAmount = amount;
        Double sourceRate = currencyRateService.read(sourceAcc.getBank().getId(), sourceAcc.getCurrency());
        Double targetRate = currencyRateService.read(targetAcc.getBank().getId(), targetAcc.getCurrency());

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
            totalAmount = currencyConverter.convert(sourceRate.getRateToByn(), targetRate.getRateToByn(), amount);
        }
        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);
        saveAccsTroughTransaction(sourceAcc, targetAcc);
        // TODO: 15.09.2021 check bad cases

        // TODO: 15.09.2021 ask. сервис взаимодействует с сервисом и в чужое ДАО не лезет. все так?
        return transferHistoryService.save(transferHistoryService.createHistory(sourceAcc, targetAcc, amount)).getId();
    }

    @Transactional(rollbackFor = Exception.class)
        //must be overridable. я хотел сделать приватным
    public void saveAccsTroughTransaction(Account sourceAcc, Account targetAcc) {
        dao.save(sourceAcc);
        dao.save(targetAcc);
        log.debug("Transaction from " + sourceAcc.getAccNumber() + " to " + targetAcc.getAccNumber() + " finished");
    }
}


