package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.dto.AccountDto;
import ru.laptseu.bankapp.models.mappers.AccountMapper;
import ru.laptseu.bankapp.models.mappers.MapperFactory;
import ru.laptseu.bankapp.models.mappers.MapperInterface;
import ru.laptseu.bankapp.repositories.AccountRepository;
import ru.laptseu.bankapp.repositories.RepositoryFactory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.util.Optional;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class AccountService implements IMaintainableService {
    private final CommissionCalculator commissionCalculator;
    private final Account entity;
    private final RepositoryFactory factory;
    private final TransferHistoryService transferHistoryService;
    private final CurrencyConverter currencyConverter;
    private final CurrencyRateService currencyRateService;
    private final BankService bankService;
    private final ClientService clientService;
    private final MapperFactory mapperFactory;
    private final CrudRepository dao = factory.get(entity.getClass());
    private final MapperInterface mapper = mapperFactory.get(entity.getClass());


    public Optional<Account> read(String num) {
        return dao.findById(num);
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
    //must be overridable. я хотел сделать приватным
    public void saveAccountsThroughTransaction(Account sourceAcc, Account targetAcc) {
        dao.save(sourceAcc);
        dao.save(targetAcc);
        log.debug("Transaction from " + sourceAcc.getAccNumber() + " to " + targetAcc.getAccNumber() + " finished");
    }

    public Account createFromDto(AccountDto newb) {
        Account newAccount = getMapper.map(newb);
        newAccount.setClient(clientService.read(Integer.valueOf(newb.getClientId())));
        newAccount.setBank(bankService.read(Integer.valueOf(newb.getBankId())));
        return newAccount;
    }
}


