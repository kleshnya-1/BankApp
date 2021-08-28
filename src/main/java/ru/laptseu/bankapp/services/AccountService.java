package ru.laptseu.bankapp.services;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.sql.SQLException;
import java.util.Calendar;

@Log4j2
//creating models from array of parameters
public class AccountService implements IMaintainableService<Account> {
    CommissionCalculator commissionCalculator = new CommissionCalculator();
    // CurrencyConverter currencyConverter = new CurrencyConverter();
    IMaintainableDAO<Account> accountDao = DaoFactory.get(Account.class);
    TransferHistoryService transferHistoryService = new TransferHistoryService();

    @Override
    public int persist(Account o) throws SQLException {
        int id = accountDao.create(o);
        return id;
    }

    @Override
    public Account create(String[] paramArr) throws SQLException {
        Account account = new Account();
        //todo ok? getBank.setId
        account.getBank().setId(Integer.parseInt(paramArr[1]));
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
    public void update(Account account) throws SQLException {
        accountDao.update(account);
    }

    @Override
    public void delete(int key) throws SQLException {
        accountDao.delete(key);
    }

    //todo.ask это же действия внутри аккаунтов. значит, и место им тут. хотя можно и вынести отдельным классом
    public void transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        TransferHistory transferHistory = new TransferHistory();
        CurrencyConverter currencyConverter = new CurrencyConverter();
        double commission = 0;
        double rate = 1;
        double totalAmount = amount;

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
            totalAmount = currencyConverter.returnConvertedAmount(sourceAcc, targetAcc, amount);
        }
        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);
        Session session = accountDao.getSession();
        accountDao.update(sourceAcc, session);
        accountDao.update(targetAcc, session);

        log.info("\nfrom acc ID " + sourceAcc.getId() + "(" + sourceAcc.getClient().getName() + ")" + " to acc ID" +
                targetAcc.getId() + "(" + targetAcc.getClient().getName() + ") transfered " + amount + sourceAcc.getCurrency()
                + " (as " + totalAmount + "" + targetAcc.getCurrency() + ") with  commission " + commission + "" + sourceAcc.getCurrency());

        //creating and persisting transfer history
        {
            transferHistory.setDate(Calendar.getInstance().toString());
            transferHistory.setClientSourceName(sourceAcc.getClient().getName());
            transferHistory.setClientTargetName(targetAcc.getClient().getName());
            transferHistory.setAccountSourceId(String.valueOf(sourceAcc.getId()));
            transferHistory.setAccountTargetId(String.valueOf(targetAcc.getId()));
            transferHistory.setBankSourceName(sourceAcc.getBank().getName());
            transferHistory.setBankTargetName(targetAcc.getBank().getName());
            transferHistory.setAmount(sourceAcc.getAmount());
            transferHistory.setAmount(amount);
            transferHistoryService.persist(transferHistory);
        }

        session.getTransaction().commit();
        session.close();

    }
}
