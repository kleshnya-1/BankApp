package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.TransferHistory;

@Service
@Getter
@RequiredArgsConstructor
public class TransferHistoryService implements IMaintainableService<TransferHistory> {
    private final TransferHistoryDAOImpl dao;

    @Override
    public void update(TransferHistory obj) {
        throw new UnsupportedOperationException();
    }

    public TransferHistory createHistory(Account sourceAcc, Account targetAcc, double amount) {
        return new TransferHistory(sourceAcc.getClient().getName(), targetAcc.getClient().getName(), sourceAcc.getAccNumber(),
                targetAcc.getAccNumber(), sourceAcc.getBank().getName(), targetAcc.getBank().getName(),
                sourceAcc.getCurrency().toString(), amount);
    }
}
