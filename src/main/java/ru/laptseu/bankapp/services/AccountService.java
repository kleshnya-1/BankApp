package ru.laptseu.bankapp.services;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.sql.SQLException;

@Log4j2
@Service
public class AccountService implements IMaintainableService<Account> {
    //todo ask. я правильно пишу аннотации или они должны быть над классом?
    // дефолтные методы тут появятся со spring boot. до этого нет возможности вызвать ДАО, пока у них разные имена (accountDao, bankDao...)

    // TODO: 09.09.2021 replace as constructor
    @Autowired
    CommissionCalculator commissionCalculator;// = new CommissionCalculator();
    @Autowired
    IMaintainableDAO<Account> accountDao;// = DaoFactory.get(Account.class);
    @Autowired
    IMaintainableDAO<TransferHistory> transferHistoryDao;// = DaoFactory.get(TransferHistory.class);
    @Autowired
    TransferHistoryService transferHistoryService;// = new TransferHistoryService();
    @Autowired
    CurrencyConverter currencyConverter;// = new CurrencyConverter();
    @Autowired
    CurrencyRateService currencyRateService;

    @Override
    public int persist(Account o) throws SQLException {
        int num = accountDao.save(o);
        // TODO: 09.09.2021 check how it works
        return num;
    }

    // TODO: 10.09.2021 for removing
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
        //todo ask. тут запрашиваем пока по Ид. а надо по номеру аккаунта, а не ИД?
        // мы же из сервисов понятия не имеем, как там у ид дела
        return accountDao.read(key);
    }

    @Override
    public Account read(Currency currency, int key) throws SQLException {
        throw new UnsupportedOperationException("Only for currencyRate");
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

    public void transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
        double commission = 0;
        double totalAmount = amount;
        // TODO: 09.09.2021 ask это для понимания. или сразу стоит такое вклчать в конструктор?
        CurrencyRate source = currencyRateService.read(sourceAcc.getCurrency(), sourceAcc.getBank().getId());
        CurrencyRate target = currencyRateService.read(targetAcc.getCurrency(), targetAcc.getBank().getId());

        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
            commission = commissionCalculator.calculate(targetAcc, amount);
        }
        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
            totalAmount = currencyConverter.convert(source.getRateToByn(), target.getRateToByn(), amount);
        }
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


