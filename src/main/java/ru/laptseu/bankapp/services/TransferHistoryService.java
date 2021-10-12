package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.models.dto.EntityDto;

import java.util.Calendar;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class TransferHistoryService extends AbstractService {
    private final Class resourceEntityClass = TransferHistory.class;

    public int save(Account sourceAcc, Account targetAcc, double amount) {
        return (save(createHistory(sourceAcc, targetAcc, amount))).getId();
    }

    public TransferHistory createHistory(Account sourceAcc, Account targetAcc, double amount) {
        return new TransferHistory(Calendar.getInstance(), sourceAcc.getClient().getName(), targetAcc.getClient().getName(), sourceAcc.getAccNumber(),
                targetAcc.getAccNumber(), sourceAcc.getBank().getName(), targetAcc.getBank().getName(),
                sourceAcc.getCurrency().toString(), amount);
    }


    @Override
    List readDto() {
        throw new UnsupportedOperationException();
    }

    @Override
    EntityDto readDto(int id) {
        throw new UnsupportedOperationException();
    }
}
