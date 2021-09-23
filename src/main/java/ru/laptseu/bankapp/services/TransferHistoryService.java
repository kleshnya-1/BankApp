package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.repositories.TransferHistoryRepository;

import java.util.Calendar;

@Service
@Getter
@RequiredArgsConstructor
public class TransferHistoryService implements IMaintainableService<TransferHistory> {
    private final TransferHistoryRepository dao;

    @Override
    public void update(TransferHistory obj) {
        throw new UnsupportedOperationException();
    }

    public TransferHistory createHistory(Account sourceAcc, Account targetAcc, double amount) {
        return new TransferHistory(Calendar.getInstance(), sourceAcc.getClient().getName(), targetAcc.getClient().getName(), sourceAcc.getAccNumber(),
                targetAcc.getAccNumber(), sourceAcc.getBank().getName(), targetAcc.getBank().getName(),
                sourceAcc.getCurrency().toString(), amount);
    }
}
