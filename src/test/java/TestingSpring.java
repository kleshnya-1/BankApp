import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.laptseu.bankapp.Main;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.dao.CurrRateDocumentsDAO;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.*;
import ru.laptseu.bankapp.services.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class TestingSpring {

    @Autowired
    BankDAOImpl bankDAO;
    @Autowired
    ClientDAOImpl clientDAO;

    @Autowired
    AccountService accountService;
    @Autowired
    BankService bankService;
    @Autowired
    ClientService clientService;
    @Autowired
    CurrencyRateService currencyRateService;
    @Autowired
    TransferHistoryService transferHistoryService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    CurrRateDocumentsDAO mongoBankRateDAO;
//
//    @Configuration
//    static class ContextConfiguration {
//        @Bean
//        public DataSource dataSourceTest() {
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName("org.h2.Driver");
//            dataSource.setUrl("jdbc:h2:mem:testdb");
//            dataSource.setUsername("sa");
//            dataSource.setPassword("password");
//            return dataSource;
//        }
////            @Bean(name = "dataSource")
////    @ConfigurationProperties(prefix="datasource-test")
////    public DataSource dataSource(){
////        return DataSourceBuilder
////                .create()
////                .build();
////    }
//        private static final String MONGO_URL = "mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test";
//
//        @Bean
//        public MongoClient mongo() {
//            return MongoClients.create(MONGO_URL);
//        }
//
//        @Bean
//        public MongoCollection currencyRatesMongoCollection()  {
//            return mongoTemplate().getCollection("test");
//        }
//
//        @Bean
//        @Primary
//        public MongoTemplate mongoTemplate() {
//            return new MongoTemplate(mongo(), "test");
//        }
//    }

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

        CurrencyRate currencyRateUsd = new CurrencyRate();
        CurrencyRate currencyRateEur = new CurrencyRate();
        currencyRateUsd.setCurrency(Currency.USD);
        currencyRateUsd.setRateToByn(50);
        currencyRateUsd.setBank(bank);
        currencyRateEur.setCurrency(Currency.EUR);
        currencyRateEur.setRateToByn(100);
        currencyRateEur.setBank(bank);

        CurrencyRate currencyRateUsd1 = new CurrencyRate();
        CurrencyRate currencyRateEur1 = new CurrencyRate();
        currencyRateUsd1.setCurrency(Currency.USD);
        currencyRateUsd1.setRateToByn(200);
        currencyRateUsd1.setBank(bank1);
        currencyRateEur1.setCurrency(Currency.EUR);
        currencyRateEur1.setRateToByn(250);
        currencyRateEur1.setBank(bank1);

        bank.getCurrencyRates().add(currencyRateUsd);
        bank.getCurrencyRates().add(currencyRateEur);
        bank1.getCurrencyRates().add(currencyRateUsd1);
        bank1.getCurrencyRates().add(currencyRateEur1);

        currencyRateService.save(currencyRateUsd);
        currencyRateService.save(currencyRateEur);
        currencyRateService.save(currencyRateUsd1);
        currencyRateService.save(currencyRateEur1);

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

        Account account1fDB = accountService.read(account1.getId());
        Account account2fDB = accountService.read(account2.getId());

        double transferAmount = 50;
       int historyNum= accountService.transferAmount(account1fDB, account2fDB, transferAmount);
        Account account1fDB2 = accountService.read(account1.getId());
        Account account2fDB2 = accountService.read(account2.getId());
        assertEquals(account1fDB.getAmount(), 962.5);
        assertEquals(account2fDB.getAmount(), 2025);
        assertEquals(account1fDB, account1fDB2);
        assertEquals(account2fDB, account2fDB2);
        assertEquals(account1fDB2.getAmount(), 962.5);
        assertEquals(account2fDB2.getAmount(), 2025);
        // TODO: 15.09.2021 bad case test (mockito)

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

    // TODO: 15.09.2021 history persisting test
    @SneakyThrows
    @Test
    public void testBankCRUD() {
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("testBankCRUD" + Calendar.getInstance().getTime());
        b2.setName("testBankCRUD2 " + Calendar.getInstance().getTime());
        b1.setTransferFeeInPercentForNotNaturalPersons(70);
        b2.setTransferFeeInPercentForNotNaturalPersons(70);
        int n1 = bankDAO.save(b1).getId();
        int n2 = bankDAO.save(b2).getId();
        Bank b1Fdb = bankDAO.read(n1);
        Bank b2Fdb = bankDAO.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        b1.setTransferFeeInPercent(50);
        b2.setTransferFeeInPercent(50);
        bankDAO.update(b1);
        bankDAO.update(b2);
        assertNotEquals(b1, b1Fdb);
        assertNotEquals(b2, b2Fdb);

        b1Fdb = bankDAO.read(n1);
        b2Fdb = bankDAO.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        bankDAO.delete(b1.getId());
        bankDAO.delete(b2.getId());
        assertThrows(EntityNotFoundException.class, () -> {
            bankDAO.read(n1);
        });
        assertThrows(EntityNotFoundException.class, () -> {
            bankDAO.read(n2);
        });
    }

    @SneakyThrows
    @Test
    public void testClientCRUD() {
        Client c1 = new Client();
        Client c2 = new Client();
        c1.setName("testClientCRUD1 " + Calendar.getInstance().getTime());
        c2.setName("testClientCRUD2 " + Calendar.getInstance().getTime());
        c1.setNaturalPerson(true);
        c2.setNaturalPerson(false);
        int n1 = clientDAO.save(c1).getId();
        int n2 = clientDAO.save(c2).getId();
        Client c1Fdb = clientDAO.read(n1);
        Client c2Fdb = clientDAO.read(n2);
        assertEquals(c1, c1Fdb);
        assertEquals(c2, c2Fdb);

        c1.setNaturalPerson(false);
        c2.setNaturalPerson(true);
        clientDAO.update(c1);
        clientDAO.update(c2);
        assertNotEquals(c1, c1Fdb);
        assertNotEquals(c2, c2Fdb);

        c1Fdb = clientDAO.read(n1);
        c2Fdb = clientDAO.read(n2);
        assertEquals(c1, c1Fdb);
        assertEquals(c2, c2Fdb);

        clientDAO.delete(c1.getId());
        clientDAO.delete(c2.getId());
        assertThrows(EntityNotFoundException.class, () -> {
            clientDAO.read(n1);
        });
        assertThrows(EntityNotFoundException.class, () -> {
            clientDAO.read(n2);
        });
    }

    @SneakyThrows
    @Test
    public void testCurrencyRateServiceCRUD() {
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("TestBank1 for CurrRate CRUD" + Calendar.getInstance().getTime());
        b2.setName("TestBank2 for CurrRate CRUD" + Calendar.getInstance().getTime());

        CurrencyRate cr1 = new CurrencyRate();
        cr1.setCurrency(Currency.EUR);
        cr1.setRateToByn(260);
        cr1.setBank(b1);
        CurrencyRate cr2 = new CurrencyRate();
        cr2.setCurrency(Currency.USD);
        cr2.setRateToByn(360);
        cr2.setBank(b2);
        CurrencyRate cr3 = new CurrencyRate();
        cr3.setCurrency(Currency.USD);
        cr3.setRateToByn(361);
        cr3.setBank(b2);
        CurrencyRate cr4 = new CurrencyRate();
        cr4.setCurrency(Currency.USD);
        cr4.setRateToByn(362);
        cr4.setBank(b2);

        b1.getCurrencyRates().add(cr1);
        b2.getCurrencyRates().add(cr2);
        b2.getCurrencyRates().add(cr3);
        b2.getCurrencyRates().add(cr4);
        bankDAO.save(b1);
        bankDAO.save(b2);
        currencyRateService.save(cr1);
        currencyRateService.save(cr2);
        currencyRateService.save(cr3);
        currencyRateService.save(cr4);

        CurrencyRate cr1fDB = currencyRateService.read(cr1.getBank().getId(), cr1.getCurrency());
        CurrencyRate cr4fDB = currencyRateService.read(cr4.getBank().getId(), cr4.getCurrency());


        assertEquals(cr1, cr1fDB);
        assertEquals(cr4, cr4fDB);

    }

    @SneakyThrows
    @Test
    public void testDocumentInMongoCRUD() {
        BankRateListDocument cd1 = new BankRateListDocument();
        BankRateListDocument cd2 = new BankRateListDocument();
        BankRateListDocument cd3 = new BankRateListDocument();

        cd1.setBankId(Integer.valueOf("-1000" + Calendar.getInstance().get(Calendar.MILLISECOND)));
        cd2.setBankId(Integer.valueOf("-2000" + Calendar.getInstance().get(Calendar.MILLISECOND)));
        cd3.setBankId(Integer.valueOf("-3000" + Calendar.getInstance().get(Calendar.MILLISECOND)));
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("testDocumentInMongoCRUD1 " + Calendar.getInstance().getTime());
        b2.setName("testDocumentInMongoCRUD2 " + Calendar.getInstance().getTime());

        CurrencyRate cr1 = new CurrencyRate();
        cr1.setCurrency(Currency.EUR);
        cr1.setRateToByn(260);
        cr1.setBank(b1);
        CurrencyRate cr2 = new CurrencyRate();
        cr2.setCurrency(Currency.USD);
        cr2.setRateToByn(360);
        cr2.setBank(b2);
        CurrencyRate cr3 = new CurrencyRate();
        cr3.setCurrency(Currency.USD);
        cr3.setRateToByn(361);
        cr3.setBank(b2);
        CurrencyRate cr4 = new CurrencyRate();
        cr4.setCurrency(Currency.USD);
        cr4.setRateToByn(362);
        cr4.setBank(b2);

        List<CurrencyRate> l1 = new ArrayList<>();
        List<CurrencyRate> l2 = new ArrayList<>();
        l1.add(cr1);
        l1.add(cr2);
        l1.add(cr3);
        l2.add(cr4);
        cd1.setCurrencies(l1);
        cd2.setCurrencies(l2);

        int s1 = mongoBankRateDAO.save(cd1).getBankId();
        int s2 = mongoBankRateDAO.save(cd2).getBankId();
        int s3 = mongoBankRateDAO.save(cd3).getBankId();

        BankRateListDocument cd1fDB = mongoBankRateDAO.readByBankId(s1);
        BankRateListDocument cd2fDB = mongoBankRateDAO.readByBankId(s2);
        BankRateListDocument cd3fDB = mongoBankRateDAO.readByBankId(s3);

        assertEquals(cd1, cd1fDB);
        assertEquals(cd2, cd2fDB);
        assertEquals(cd3, cd3fDB);

        mongoBankRateDAO.delete(s1);
        mongoBankRateDAO.delete(s2);
        mongoBankRateDAO.delete(s3);

        assertThrows(EntityNotFoundException.class, () -> {
            mongoBankRateDAO.read(s1);
            ;
        });
        assertThrows(EntityNotFoundException.class, () -> {
            mongoBankRateDAO.read(s2);
        });
        assertThrows(EntityNotFoundException.class, () -> {
            mongoBankRateDAO.read(s3);
        });

    }

}