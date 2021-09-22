import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.laptseu.bankapp.Main;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.*;
import ru.laptseu.bankapp.models.testModels.AccountForTest;
import ru.laptseu.bankapp.models.testModels.BankForTest;
import ru.laptseu.bankapp.models.testModels.ClientForTest;
import ru.laptseu.bankapp.services.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class TestingSpring {

    @Autowired
    private AccountService accountService;
    @Autowired
    private BankService bankService;
    @Autowired
    private ClientService clientService;
    @Autowired
    //ask. initialisation special test-bean for testing without DB. is it what we spoke about?
    private CurrencyRateServiceForTest currencyRateService;
    @Autowired
    private TransferHistoryService transferHistoryService;

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

        BankRateListDocument bankRateListDocument = new BankRateListDocument();
        bankRateListDocument.setBankId(bank.getId());
        Map<Currency, Double> currencyDoubleMap = new HashMap<>();
        currencyDoubleMap.put(Currency.USD, 50d);
        currencyDoubleMap.put(Currency.EUR, 100d);
        bankRateListDocument.setCurrenciesAndRates(currencyDoubleMap);
        currencyRateService.save(bankRateListDocument);

        Map<Currency, Double> currencyDoubleMap1 = new HashMap<>();
        currencyDoubleMap1.put(Currency.USD, 200d);
        currencyDoubleMap1.put(Currency.EUR, 250d);
        BankRateListDocument bankRateListDocument1 = new BankRateListDocument();
        bankRateListDocument1.setBankId(bank1.getId());
        bankRateListDocument1.setCurrenciesAndRates(currencyDoubleMap1);
        currencyRateService.save(bankRateListDocument1);

        bankService.update(bank);
        bankService.update(bank1);

        Client client1 = new ClientForTest();
        Client client2 = new ClientForTest();
        client1.setName("ClientTransactionTest");
        client1.setNaturalPerson(true);
        client2.setName("ClientTransactionTest1");
        client2.setNaturalPerson(false);
        clientService.save(client1);
        clientService.save(client2);

        Account account1 = new AccountForTest();
        Account account1p2 = new AccountForTest();
        Account account2 = new AccountForTest();
        account1.setAmount(1000);
        account1.setBank(bank);
        account1.setCurrency(Currency.EUR);
        account1p2.setAmount(1200);
        account1p2.setBank(bank1);
        account1p2.setCurrency(Currency.USD);
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

        AccountForTest account1fDB = (AccountForTest) accountService.read(account1.getId());
        AccountForTest account2fDB = (AccountForTest) accountService.read(account2.getId());

        double transferAmount = 50;
        int historyNum = accountService.transferAmount(account1fDB, account2fDB, transferAmount);
        AccountForTest account1fDB2 = (AccountForTest) accountService.read(account1.getId());
        AccountForTest account2fDB2 = (AccountForTest) accountService.read(account2.getId());
        assertEquals(account1fDB.getAmount(), 962.5);
        assertEquals(account2fDB.getAmount(), 2025);
        assertEquals(account1fDB, account1fDB2);
        assertEquals(account2fDB, account2fDB2);
        assertEquals(account1fDB2.getAmount(), 962.5);
        assertEquals(account2fDB2.getAmount(), 2025);
        // TODO: 15.09.2021 bad case test (mockito)
        // TODO: 15.09.2021 test for array

        TransferHistory thFdb = transferHistoryService.read(historyNum);
        assertTrue(thFdb.getDate().before(Calendar.getInstance()));
        assertTrue(thFdb.getDate().after(startTime));
        assertEquals(thFdb.getClientSourceName(), account1fDB.getClient().getName());
        assertEquals(thFdb.getClientTargetName(), account2fDB.getClient().getName());
        assertEquals(thFdb.getAccSourceNum(), account1fDB.getAccNumber());
        assertEquals(thFdb.getAccTargetNum(), account2fDB.getAccNumber());
        assertEquals(thFdb.getBankSourceName(), account1fDB.getBank().getName());
        assertEquals(thFdb.getBankTargetName(), account2fDB.getBank().getName());
        assertEquals(thFdb.getCurrency(), account1fDB.getCurrency().toString());
        assertEquals(thFdb.getAmount(), transferAmount);
    }

    @SneakyThrows
    @Test
    public void testBankCRUD() {
        Bank b1 = new BankForTest();
        Bank b2 = new BankForTest();
        b1.setName("testBankCRUD" + Calendar.getInstance().getTime());
        b2.setName("testBankCRUD2 " + Calendar.getInstance().getTime());
        b1.setTransferFeeInPercentForNotNaturalPersons(70);
        b2.setTransferFeeInPercentForNotNaturalPersons(70);
        int n1 = bankService.save(b1).getId();
        int n2 = bankService.save(b2).getId();
        Bank b1Fdb = bankService.read(n1);
        Bank b2Fdb = bankService.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        b1.setTransferFeeInPercent(50);
        b2.setTransferFeeInPercent(50);
        bankService.update(b1);
        bankService.update(b2);
        assertNotEquals(b1, b1Fdb);
        assertNotEquals(b2, b2Fdb);
        b1Fdb = bankService.read(n1);
        b2Fdb = bankService.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        bankService.delete(b1);
        bankService.delete(b2);
        assertThrows(NoSuchElementException.class, () -> {
            bankService.read(n1);
        });
        assertThrows(NoSuchElementException.class, () -> {
            bankService.read(n2);
        });
    }

    @SneakyThrows
    @Test
    public void testClientCRUD() {
        Client c1 = new ClientForTest();
        Client c2 = new ClientForTest();
        c1.setName("testClientCRUD1 " + Calendar.getInstance().getTime());
        c2.setName("testClientCRUD2 " + Calendar.getInstance().getTime());
        c1.setNaturalPerson(true);
        c2.setNaturalPerson(false);
        int n1 = clientService.save(c1).getId();
        int n2 = clientService.save(c2).getId();
        Client c1Fdb = clientService.read(n1);
        Client c2Fdb = clientService.read(n2);
        assertEquals(c1, c1Fdb);
        assertEquals(c2, c2Fdb);

        c1.setNaturalPerson(false);
        c2.setNaturalPerson(true);
        clientService.update(c1);
        clientService.update(c2);
        assertNotEquals(c1, c1Fdb);
        assertNotEquals(c2, c2Fdb);
        c1Fdb = clientService.read(n1);
        c2Fdb = clientService.read(n2);
        assertEquals(c1, c1Fdb);
        assertEquals(c2, c2Fdb);

        clientService.delete(c1);
        clientService.delete(c2);
        assertThrows(NoSuchElementException.class, () -> {
            clientService.read(n1);
        });
        assertThrows(NoSuchElementException.class, () -> {
            clientService.read(n2);
        });
    }

    @SneakyThrows
    @Test
    public void testCurrencyRateServiceCRUD() {
        BankRateListDocument bankRateListDocument1 = new BankRateListDocument();
        bankRateListDocument1.setBankId(Calendar.getInstance().get(Calendar.MILLISECOND));
        Map<Currency, Double> map1 = new HashMap<>();
        map1.put(Currency.EUR, 260d);
        map1.put(Currency.EUR, 261d);
        bankRateListDocument1.setCurrenciesAndRates(map1);
        currencyRateService.save(bankRateListDocument1);
        Double cr1fDB = currencyRateService.read(bankRateListDocument1.getBankId(), Currency.EUR);
        assertEquals(261d, cr1fDB);

        BankRateListDocument bankRateListDocument2 = new BankRateListDocument();
        bankRateListDocument2.setBankId(bankRateListDocument1.getBankId() + 1);
        Map<Currency, Double> map2 = new HashMap<>();
        map2.put(Currency.USD, 360d);
        map2.put(Currency.USD, 361d);
        map2.put(Currency.USD, 362d);
        bankRateListDocument2.setCurrenciesAndRates(map2);
        currencyRateService.save(bankRateListDocument2);
        Double cr2fDB = currencyRateService.read(bankRateListDocument2.getBankId(), Currency.USD);
        assertEquals(362d, cr2fDB);
        // TODO: 21.09.2021 delete from Db
        assertEquals(bankRateListDocument1, currencyRateService.read(bankRateListDocument1.getBankId()));
        assertEquals(bankRateListDocument2, currencyRateService.read(bankRateListDocument2.getBankId()));
        currencyRateService.delete(bankRateListDocument1.getBankId());
        currencyRateService.delete(bankRateListDocument2.getBankId());
        assertNull(currencyRateService.read(bankRateListDocument1.getBankId()));
        assertNull(currencyRateService.read(bankRateListDocument2.getBankId()));
    }

    @SneakyThrows
    @Test
    public void testDocumentInMongoCRUD() {
        BankRateListDocument cd1 = new BankRateListDocument();
        BankRateListDocument cd2 = new BankRateListDocument();
        BankRateListDocument cd3 = new BankRateListDocument();
        cd1.setBankId(Integer.valueOf("10" + Calendar.getInstance().get(Calendar.MILLISECOND)));
        cd2.setBankId(Integer.valueOf("20" + Calendar.getInstance().get(Calendar.MILLISECOND)));
        cd3.setBankId(Integer.valueOf("30" + Calendar.getInstance().get(Calendar.MILLISECOND)));
        Map<Currency, Double> currencyDoubleMap = new HashMap<>();
        currencyDoubleMap.put(Currency.EUR, 260d);
        cd1.setCurrenciesAndRates(currencyDoubleMap);

        Map<Currency, Double> currencyDoubleMap1 = new HashMap<>();
        currencyDoubleMap1.put(Currency.USD, 360d);
        currencyDoubleMap1.put(Currency.USD, 361d);
        currencyDoubleMap1.put(Currency.USD, 362d);
        cd2.setCurrenciesAndRates(currencyDoubleMap1);

        int s1 = currencyRateService.save(cd1).getBankId();
        int s2 = currencyRateService.save(cd2).getBankId();
        int s3 = currencyRateService.save(cd3).getBankId();

        BankRateListDocument cd1fDB = currencyRateService.read(s1);
        BankRateListDocument cd2fDB = currencyRateService.read(s2);
        BankRateListDocument cd3fDB = currencyRateService.read(s3);
        assertEquals(cd1.getDate(), cd1fDB.getDate());
        assertEquals(cd2.getDate(), cd2fDB.getDate());
        assertEquals(cd3.getDate(), cd3fDB.getDate());
        currencyRateService.delete(cd1);
        currencyRateService.delete(cd2);
        currencyRateService.delete(cd3);
        BankRateListDocument cd1fDBaD = currencyRateService.read(s1);
        BankRateListDocument cd2fDBaD = currencyRateService.read(s2);
        BankRateListDocument cd3fDBaD = currencyRateService.read(s3);

        assertNull(cd1fDBaD);
        assertNull(cd2fDBaD);
        assertNull(cd3fDBaD);
    }

    //in progress
//    @SneakyThrows
//    @Test
//    public void testFactory() {
//
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
//        AccountRepo oo = applicationContext.getBean(AccountRepo.class);
//        String[] a = applicationContext.getBeanDefinitionNames();
//        CrudRepository ccc = daoFactory.get(Account.class);
//        assertEquals(ccc, AccountRepo.class);
//        assertEquals(daoFactory.get(Account.class), AccountRepo.class);
//    }
}