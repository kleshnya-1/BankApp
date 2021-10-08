package ru.laptseu.bankapp.test;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.laptseu.bankapp.Main;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.*;
import ru.laptseu.bankapp.repositories.AccountRepository;
import ru.laptseu.bankapp.repositories.RepositoryFactory;
import ru.laptseu.bankapp.services.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//@DataMongoTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class TestingSpring {

    @Autowired
    private CurrencyRateService currencyRateService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BankService bankService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransferHistoryService transferHistoryService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RepositoryFactory repositoryFactory;


    @SneakyThrows
    @Test
    public void testTransfer() {

        Calendar startTime = new GregorianCalendar();
        Bank bank = new Bank();
        Bank bank1 = new Bank();
        bank.setName("testTransfer01 " + Calendar.getInstance().getTime());
        bank.setTransferFeeInPercent(10);
        bank.setTransferFeeInPercentForNotNaturalPersons(20);

        bank1.setTransferFeeInPercent(15);
        bank1.setTransferFeeInPercentForNotNaturalPersons(25);
        bank1.setName("testTransfer02 " + Calendar.getInstance().getTime());
        bankService.save(bank);
        bankService.save(bank1);

        BankRateList bankRateList = new BankRateList();
        bankRateList.setBankId(bank.getId());
        Map<Currency, Double> currencyDoubleMap = new HashMap<>();
        currencyDoubleMap.put(Currency.USD, 50d);
        currencyDoubleMap.put(Currency.EUR, 100d);
        bankRateList.setCurrenciesAndRates(currencyDoubleMap);
        currencyRateService.save(bankRateList);

        Map<Currency, Double> currencyDoubleMap1 = new HashMap<>();
        currencyDoubleMap1.put(Currency.USD, 200d);
        currencyDoubleMap1.put(Currency.EUR, 250d);
        BankRateList bankRateList1 = new BankRateList();
        bankRateList1.setBankId(bank1.getId());
        bankRateList1.setCurrenciesAndRates(currencyDoubleMap1);
        currencyRateService.save(bankRateList1);

        bankService.update(bank);
        bankService.update(bank1);

        Client client1 = new Client();
        Client client2 = new Client();
        client1.setName("ClientTransactionTest");
        client1.setNaturalPerson(true);
        client2.setName("ClientTransactionTest1");
        client2.setNaturalPerson(false);
        clientService.save(client1);
        clientService.save(client2);

        Account account1 = new Account();
        Account account1p2 = new Account();
        Account account2 = new Account();
        account1.setAmount(1000);
        account1.setBank(bank);
        account1.setCurrency(Currency.EUR);

        account2.setAmount(2000);
        account2.setBank(bank1);
        account2.setCurrency(Currency.USD);

        accountService.save(account1);
        accountService.save(account1p2);
        accountService.save(account2);
        client1.getAccounts().add(account1);
        account1.setClient(client1);
        client1.getAccounts().add(account1p2);
        account1p2.setClient(client1);
        client2.getAccounts().add(account2);
        account2.setClient(client2);

        bankService.update(bank);
        bankService.update(bank1);
        clientService.update(client1);
        clientService.update(client2);

        double transferAmount = 50;
        int historyNum = accountService.transferAmount(account1, account2, transferAmount);
        Account account1fDB2 =  accountService.read(account1.getId());
        Account account2fDB2 =  accountService.read(account2.getId());
        assertEquals(account1.getAmount(), 962.5);
        assertEquals(account2.getAmount(), 2025);
        assertEquals(account1.getId(), account1fDB2.getId());
        assertEquals(account2.getId(), account2fDB2.getId());
        assertEquals(account1fDB2.getAmount(), 962.5);
        assertEquals(account2fDB2.getAmount(), 2025);
        // TODO: 15.09.2021 bad case test (mockito)
        // TODO: 15.09.2021 test for array

        TransferHistory thFdb = transferHistoryService.read(historyNum);
        assertTrue(thFdb.getDate().before(Calendar.getInstance()));
        assertTrue(thFdb.getDate().after(startTime));
        assertEquals(thFdb.getClientSourceName(), account1.getClient().getName());
        assertEquals(thFdb.getClientTargetName(), account2.getClient().getName());
        assertEquals(thFdb.getAccSourceNum(), account1.getAccNumber());
        assertEquals(thFdb.getAccTargetNum(), account2.getAccNumber());
        assertEquals(thFdb.getBankSourceName(), account1.getBank().getName());
        assertEquals(thFdb.getBankTargetName(), account2.getBank().getName());
        assertEquals(thFdb.getCurrency(), account1.getCurrency().toString());
        assertEquals(thFdb.getAmount(), transferAmount);
    }










    //in progress
    @SneakyThrows
    @Test
    public void testFactory() {
//        var f = repositoryFactory.get(Account.class);
//        var f1 = repositoryFactory.get(Bank.class);
//        var f2 = repositoryFactory.get(BankRateListDocument.class);
    }
}