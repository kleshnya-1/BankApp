package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.TransferHistory;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TransferHistoryService implements IMaintainableService<TransferHistory> {

    private final TransferHistoryDAOImpl transferHistoryDAO;

//    public TransferHistory create(String clientSourceName, String clientTargetName,
//                                  String accSourceNum, String accTargetNum, String bankSourceName,
//                                  String bankTargetName, String currency, double amount) throws SQLException {
//
//        return new TransferHistory(clientSourceName, clientTargetName,
//                accSourceNum, accTargetNum, bankSourceName,
//                bankTargetName, currency, amount);
//
//    }

    @Override
    public int save(TransferHistory obj) throws SQLException {
        int id = transferHistoryDAO.save(obj).getId();
        return id;
    }

    @Override
    public TransferHistory read(int key) throws Throwable {
        return transferHistoryDAO.read(key);
    }



    @Override
    public void update(TransferHistory obj) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int key) throws SQLException {
        transferHistoryDAO.delete(key);
    }
}
