package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
import ru.laptseu.bankapp.models.TransferHistory;

@Service
@RequiredArgsConstructor
public class TransferHistoryService implements IMaintainableService<TransferHistory> {

    private final TransferHistoryDAOImpl transferHistoryDAO;

    @Override
    public int save(TransferHistory obj) {
        int id = transferHistoryDAO.save(obj).getId();
        return id;
    }

    @Override
    public TransferHistory read(int key) throws Throwable {
        return transferHistoryDAO.read(key);
    }


    @Override
    public void update(TransferHistory obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int key) {
        transferHistoryDAO.delete(key);
    }
}
