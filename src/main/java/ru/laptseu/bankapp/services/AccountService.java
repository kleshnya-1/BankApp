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

@Log4j2
//creating models from array of parameters
public class AccountService implements IMaintainableService<Account> {
    CommissionCalculator commissionCalculator = new CommissionCalculator();

    IMaintainableDAO<Account> accountDao = DaoFactory.get(Account.class);
    IMaintainableService transferHistoryService = ServiceFactory.get(TransferHistory.class);
    IMaintainableDAO transferHistoryDao = DaoFactory.get(TransferHistory.class);
    CurrencyConverter currencyConverter = new CurrencyConverter();

    @Override
    public int persist(Account o) throws SQLException {
        int id = accountDao.create(o);
        return id;
    }

    @Override
    public Account create(String[] paramArr) throws SQLException {
        Account account = new Account();
        //todo.ask ok? getBank.setId
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
        double commission = 0;
        double totalAmount = amount;

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
            totalAmount = currencyConverter.returnConvertedAmount(sourceAcc, targetAcc, amount);
        }
        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);

        //creating and persisting unsuccessful transfer history
        TransferHistory history = new TransferHistory(sourceAcc.getClient().getName(), targetAcc.getClient().getName(),
                String.valueOf(sourceAcc.getId()), String.valueOf(targetAcc.getId()), sourceAcc.getBank().getName(),
                targetAcc.getBank().getName(), sourceAcc.getCurrency().toString(), amount);
        transferHistoryService.persist(history);
        log.info("\nfrom acc ID " + sourceAcc.getId() + "(" + sourceAcc.getClient().getName() + ")" + " to acc ID" +
                targetAcc.getId() + "(" + targetAcc.getClient().getName() + ") transfered " + amount + sourceAcc.getCurrency()
                + " (as " + totalAmount + "" + targetAcc.getCurrency() + ") with  commission " + commission + "" + sourceAcc.getCurrency());
        Session session = accountDao.getSession();
        history.setSuccess(true);
        accountDao.update(sourceAcc, session);
        accountDao.update(targetAcc, session);

        //todo ask. here we will use directly DAO
        //updating history
        transferHistoryDao.update(history, session);

        session.getTransaction().commit();
        session.close();
    }
}
