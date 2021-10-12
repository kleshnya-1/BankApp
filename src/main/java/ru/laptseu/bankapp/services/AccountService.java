package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.dto.AccountDto;
import ru.laptseu.bankapp.models.mappers.AccountMapper;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class AccountService extends AbstractService {
    private final Class resourceEntityClass = Account.class;
    private final CommissionCalculator commissionCalculator;
    private final TransferHistoryService transferHistoryService;
    private final CurrencyConverter currencyConverter;
    private final BankService bankService;
    private final ClientService clientService;

    public List<AccountDto> readDto() {
        List<Account> accountList = (List<Account>) read();
        return accountList.stream().map(entity -> AccountMapper.INSTANCE.map(entity)).collect(Collectors.toList());
    }

    public AccountDto readDto(int id) {
        return AccountMapper.INSTANCE.map(read(id));
    }

    public Account fromDto(AccountDto newb) {
        Account newAccount = AccountMapper.INSTANCE.map(newb);
        newAccount.setClient(clientService.read(Integer.valueOf(newb.getClientId())));
        newAccount.setBank(bankService.read(Integer.valueOf(newb.getBankId())));
        return newAccount;
    }

    public int transferAmount(Account sourceAcc, Account targetAcc, double amount) {
        double commission = 0;
        double totalAmount = amount;

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
            totalAmount = currencyConverter.convert(sourceAcc.getBank().getId(), sourceAcc.getCurrency(),
                    targetAcc.getBank().getId(), targetAcc.getCurrency(), amount);
        }
        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);
        saveAccountsThroughTransaction(sourceAcc, targetAcc);
        // TODO: 15.09.2021 check bad cases
        return transferHistoryService.save(sourceAcc, targetAcc, amount);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAccountsThroughTransaction(Account sourceAcc, Account targetAcc) {
        getRepository().save(sourceAcc);
        getRepository().save(targetAcc);
        log.debug("Transaction from " + sourceAcc.getAccNumber() + " to " + targetAcc.getAccNumber() + " finished");
    }
}


