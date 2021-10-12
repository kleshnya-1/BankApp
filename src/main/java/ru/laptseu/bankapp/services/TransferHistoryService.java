package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.models.mappers.MapperFactory;
import ru.laptseu.bankapp.repositories.RepositoryFactory;
import ru.laptseu.bankapp.repositories.TransferHistoryRepository;

import java.util.Calendar;

@Service
@Getter
@RequiredArgsConstructor
public class TransferHistoryService implements IMaintainableService {
    private final TransferHistory entity;

    private final RepositoryFactory repositoryFactory;
    private final MapperFactory mapperFactory;
    private final ServiceFactory serviceFactory;

    public int save(Account sourceAcc, Account targetAcc, double amount) {
        return (save(createHistory(sourceAcc, targetAcc, amount))).getId();
    }

    public TransferHistory createHistory(Account sourceAcc, Account targetAcc, double amount) {
        return new TransferHistory(Calendar.getInstance(), sourceAcc.getClient().getName(), targetAcc.getClient().getName(), sourceAcc.getAccNumber(),
                targetAcc.getAccNumber(), sourceAcc.getBank().getName(), targetAcc.getBank().getName(),
                sourceAcc.getCurrency().toString(), amount);
    }
}
