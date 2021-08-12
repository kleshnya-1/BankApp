package ru.laptseu.bankApp.services;

import ru.laptseu.bankApp.dao.TransferHistoryDAO;

public class TransactionService {
    TransferHistoryDAO transferHistoryDAO = new TransferHistoryDAO();


    public void showTransactionByNameAndDate(String name, int days) {

        transferHistoryDAO.showTransactionByNameAndDate(name, days);

    }
}
