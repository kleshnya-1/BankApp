package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.TransferHistoryDAO;

public class TransactionService {
    private final TransferHistoryDAO transferHistoryDAO = new TransferHistoryDAO();

    public void showTransactionByNameAndDate(String name, int days) {
        transferHistoryDAO.showTransactionByNameAndDate(name, days);
    }
}
