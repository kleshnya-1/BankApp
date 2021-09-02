package ru.laptseu.bankapp.services;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;
import ru.laptseu.bankapp.utilities.NumberGeneratorForAccounts;

import java.sql.SQLException;

@Log4j2
public class AccountService implements IMaintainableService<Account> {
    //todo ask. я правильно пишу аннотации или они должны быть над классом?
    // дефолтные методы тут появятся со spring boot. до этого нет возможности вызвать ДАО, пока у них разные имена
    CommissionCalculator commissionCalculator = new CommissionCalculator();

    IMaintainableDAO<Account> accountDao = DaoFactory.get(Account.class);
    IMaintainableDAO<TransferHistory> transferHistoryDao = DaoFactory.get(TransferHistory.class);
    TransferHistoryService transferHistoryService = new TransferHistoryService();
    CurrencyConverter currencyConverter = new CurrencyConverter();

    @Override
    public int persist(Account o) throws SQLException {
        while (o.getAccNumber() == null) {
            int number = NumberGeneratorForAccounts.generate(o.getBank(), o.getClient());
            try {
                read(number);
            } catch (EntityNotFoundException e) {
                o.setAccNumber(number);
            }
        }
        accountDao.save(o);
        return o.getAccNumber();
    }

    @Override
    //creating models from array of parameters
    public Account create(String[] paramArr) throws SQLException {
        Account account = new Account();
        //todo.ask ok? getBank.setId
        account.getBank().setName(paramArr[1]);
        account.setCurrency(Currency.valueOf(paramArr[2]));
        account.setAmount(Double.parseDouble(paramArr[3]));
        return account;
    }

    @Override
    public Account read(int key) throws SQLException {
        //todo ask. тут запрашиваем по номеру аккаунта, а не ИД?
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

    //todo. refactor
    public void transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        double commission = 0;
        double totalAmount = amount;

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }

//todo bank.getСurrency()
        // totalAmount = currencyConverter.returnConvertedAmount(sourceAcc.getBank()., targetAcc, amount);
        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);

        try (Session session = accountDao.getSession()) {
            accountDao.update(sourceAcc, session);
            accountDao.update(targetAcc, session);
            session.getTransaction().commit();
            session.close();
            log.info("\nfrom acc ID " + sourceAcc.getId() + "(" + sourceAcc.getClient().getName() + ")" + " to acc ID" +
                    targetAcc.getId() + "(" + targetAcc.getClient().getName() + ") transfered " + amount + sourceAcc.getCurrency()
                    + " (as " + totalAmount + "" + targetAcc.getCurrency() + ") with  commission " + commission + "" + sourceAcc.getCurrency());
            transferHistoryDao.save(transferHistoryService.create(sourceAcc.getClient().getName(), targetAcc.getClient().getName(),
                    sourceAcc.getAccNumber(), targetAcc.getAccNumber(), sourceAcc.getBank().getName(),
                    targetAcc.getBank().getName(), sourceAcc.getCurrency().toString(), amount));
        }
    }
}


