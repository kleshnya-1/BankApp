package ru.laptseu.bankapp.services;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
//creating models from array of parameters
public class AccountService implements IMaintainableService<Account> {
    CommissionCalculator commissionCalculator = new CommissionCalculator();
    CurrencyConverter currencyConverter = new CurrencyConverter();
    IMaintainableDAO<Account> accountDao = DaoFactory.get(Account.class);
    TransferHistoryDAOImpl transferHistoryDAOImpl = new TransferHistoryDAOImpl();

    @Override
    public int persist(Account o) throws SQLException {
        int id = accountDao.create(o);
        return id;
    }

    @Override
    public Account create(String[] paramArr) throws SQLException {
        Account account = new Account();
        account.setClientId(Integer.parseInt(paramArr[0]));
        account.setBankId(Integer.parseInt(paramArr[1]));
        account.setCurrency(Currency.valueOf(paramArr[2]));
        account.setAmount(Double.parseDouble(paramArr[3]));
        return account;
    }

    @Override
    public Account read(int key) throws SQLException {
        return accountDao.read(key);
    }

    @Override
    public void update(String[] paramArr) throws SQLException {
        Account account;
        account = create(paramArr);
        account.setId(Integer.parseInt(paramArr[paramArr.length]));
        accountDao.update(account);
    }

    @Override
    public void delete(int key) throws SQLException {
        accountDao.delete(key);
    }

    //todo
    public boolean transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
//        TransferHistory transferHistory = new TransferHistory();
//        double commission = 0;
//        double rate = 1;
//        double totalAmount = amount;
//
//        //todo logic in progress
//        if (sourceAcc.getBankId() != (targetAcc.getBankId())) {
//            commission = commissionCalculator.calculate(targetAcc, amount);
//        }
//        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
//            totalAmount = currencyConverter.returnConvertedAmount(sourceAcc, targetAcc, amount);
//        }
//        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
//        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);
//        Connection connection = accountDao.getSession();
//                    accountDao.update(sourceAcc, connection);
//            accountDao.update(targetAcc, connection);
//            connection.commit();
//            connection.close();


        //todo creating and persisting transfer history
 /*       transferHistory.setDate(Calendar.getInstance());
        transferHistory.setFromC(sourceAcc.getClientName());
        transferHistory.setToC(targetAcc.getClientName());
        transferHistory.setFromA(sourceAcc);
        transferHistory.setToA(targetAcc);
        transferHistory.setFromB(sourceAcc.getBankName());
        transferHistory.setToB(targetAcc.getBankName());
        transferHistory.setAmount(amount);
        transferHistoryDAOImpl.create(transferHistory);*/
        return true;
    }
}
