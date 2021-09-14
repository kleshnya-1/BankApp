package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.AccountRepo;
import ru.laptseu.bankapp.dao.TransHisRep;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.sql.SQLException;

@Log4j2
@Getter
@Service
public class AccountService implements IMaintainableService<Account> {
    //todo ask. я правильно пишу аннотации или они должны быть над классом?
    // дефолтные методы тут появятся со spring boot. до этого нет возможности вызвать ДАО, пока у них разные имена (accountDao, bankDao...)

    // TODO: 09.09.2021 replace as constructor

    @Autowired private CommissionCalculator commissionCalculator;
    @Autowired private  AccountRepo accountRepo;
    @Autowired private TransHisRep transHisRep;
    @Autowired private  TransferHistoryService transferHistoryService;
    @Autowired private CurrencyConverter currencyConverter;
    @Autowired private CurrencyRateService currencyRateService;
    @Autowired private   SessionFactory sessionFactory;


    //for DAO factory.
    public AccountService(){
    }

    @Override
    public int save(Account o) throws SQLException {
        accountRepo.save(o);
        // TODO: 09.09.2021 check how it works
        return 1;
    }

    @Override
    public Account read(int key) throws SQLException {
        //todo ask. тут запрашиваем пока по Ид. а надо по номеру аккаунта, а не ИД?
        // мы же из сервисов понятия не имеем, как там у ид дела
        return accountRepo.readById(key);
    }

   // @Override
    public Account read(Currency currency, int key) throws SQLException {
        throw new UnsupportedOperationException("Only for currencyRate");
    }

    @Override
    public void update(Account account) throws SQLException {
        accountRepo.save(account);
    }

    @Override
    public void delete(int key)  {
        accountRepo.deleteById(key);
    }

    public int transferAmount(Account sourceAcc, Account targetAcc, double amount) throws SQLException {
//        double commission = 0;
//        double totalAmount = amount;
//        int historyId = -1;
//        // TODO: 09.09.2021 ask это для понимания. или сразу стоит такое вклчать в конструктор?
//        CurrencyRate source = currencyRateService.read(sourceAcc.getCurrency(), sourceAcc.getBank().getId());
//        CurrencyRate target = currencyRateService.read(targetAcc.getCurrency(), targetAcc.getBank().getId());
//
//        if (!sourceAcc.getBank().equals(targetAcc.getBank())) {
//            commission = commissionCalculator.calculate(targetAcc, amount);
//        }
//        if (!sourceAcc.getCurrency().equals(targetAcc.getCurrency())) {
//            totalAmount = currencyConverter.convert(source.getRateToByn(), target.getRateToByn(), amount);
//        }
//        sourceAcc.setAmount(sourceAcc.getAmount() - commission - totalAmount);
//        targetAcc.setAmount(targetAcc.getAmount() + totalAmount);
//
//        try (Session accountDaoSession = sessionFactory.openSession()) {
//            accountDao.save(targetAcc, accountDaoSession);
//            accountDao.save(sourceAcc, accountDaoSession);
//            accountDaoSession.getTransaction().commit();
//            accountDaoSession.close();
//            log.info("\nfrom acc ID " + sourceAcc.getId() + "(" + sourceAcc.getClient().getName() + ")" + " to acc ID" +
//                    targetAcc.getId() + "(" + targetAcc.getClient().getName() + ") transfered " + amount + sourceAcc.getCurrency()
//                    + " (as " + totalAmount + "" + targetAcc.getCurrency() + ") with  commission " + commission + "" + sourceAcc.getCurrency());
//            historyId = transferHistoryDao.save(transferHistoryService.create(
//                    sourceAcc.getClient().getName(),
//                    targetAcc.getClient().getName(),
//                    sourceAcc.getAccNumber(),
//                    targetAcc.getAccNumber(),
//                    sourceAcc.getBank().getName(),
//                    targetAcc.getBank().getName(),
//                    sourceAcc.getCurrency().toString(),
//                    amount));
//        }
        return 1;
    }
}


