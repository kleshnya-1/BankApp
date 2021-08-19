package ru.laptseu.bankapp.services;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
//creating models from array of parameters
public class AccountService implements IMaintainableService<Account> {
    private final CommissionCalculator commissionCalculator = new CommissionCalculator();

    IMaintainableDAO mainDao = DaoFactory.get(Account.class);
    TransferHistoryDAOImpl transferHistoryDAOImpl = new TransferHistoryDAOImpl();
    PgPersister pgPersister = new PgPersister();

    // is it good idea to use the method?
    public int persist(Account o) throws SQLException {
        int acId = 0;
        boolean result = false;
        try {
            acId = pgPersister.persist(o);
            result = true;
        } catch (SQLException e) {
            log.error(e);
            throw e;
        }
        return acId;
    }

    @Override
    public Account create(String[] paramArr) throws SQLException {
        Account account = new Account();
        account.setClientName(paramArr[0]);
        account.setBankId(Integer.parseInt(paramArr[1]));
        account.setCurrency(Currency.valueOf(paramArr[2]));
        account.setAmount(Double.parseDouble(paramArr[3]));
        return account;
    }

    @Override
    public Account read(int key) throws SQLException {
//generic doesnt work
        return (Account) mainDao.read(key);
    }

    @Override
    public boolean update(String[] paramArr) throws SQLException {
        int accountId = Integer.parseInt(paramArr[0]);
        String[] paramArrForCreating = paramArr;
        paramArrForCreating[0] = null;
        //null always moves to the end of array. right?
        Account account;
        try {
            account = create(paramArrForCreating);
            account.setId(accountId);
            pgPersister.update(account);
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }
        return false;
    }

    @Override
    public boolean delete(int key) throws SQLException {

        boolean result = false;
        try {
            result = mainDao.delete(key);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
        return result;
    }

    public boolean transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        TransferHistory transferHistory = new TransferHistory();
        double commission = 0;
        double rate = 1;
        double totalAmount = amount;

        //todo logic in progress
      /*  if (sourceAcc.getBankId()!=(targetAcc.getBankId())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {

        }*/

        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);

        //transaction logic
        Connection connection = mainDao.getConnection();
        try {
            mainDao.update(sourceAcc, connection);
            mainDao.update(targetAcc, connection);
            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            log.error(throwables);
            throw throwables;
        }

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
