package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
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

}
