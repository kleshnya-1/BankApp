package ru.laptseu.bankapp.services;

import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.TransferHistory;

import java.sql.SQLException;

@Service
public class TransferHistoryService implements IMaintainableService<TransferHistory> {
    IMaintainableDAO<TransferHistory> transactionDao = DaoFactory.get(TransferHistory.class);

    @Override
    public TransferHistory create(String[] paramArr) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public TransferHistory create(String clientSourceName, String clientTargetName,
                                  int accSourceNum, int accTargetNum, String bankSourceName,
                                  String bankTargetName, String currency, double amount) throws SQLException {

        return new TransferHistory(clientSourceName, clientTargetName,
                accSourceNum, accTargetNum, bankSourceName,
                bankTargetName, currency, amount);

    }

    @Override
    public int persist(TransferHistory obj) throws SQLException {
        int id = transactionDao.save(obj);
        return id;
    }

    @Override
    public TransferHistory read(int key) throws SQLException {
        return transactionDao.read(key);
    }

    @Override
    public void update(String[] paramArr) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(TransferHistory obj) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int key) throws SQLException {
        transactionDao.delete(key);
    }
}
