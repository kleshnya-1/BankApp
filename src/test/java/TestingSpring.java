import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.core.Main;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
import ru.laptseu.bankapp.models.*;
import ru.laptseu.bankapp.services.*;

import java.util.Calendar;

import static com.mongodb.client.model.Filters.eq;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class TestingSpring {

    @Autowired
    BankDAOImpl bankDAO;
    @Autowired
    ClientDAOImpl clientDAO;
    @Autowired
CurrencyRateDAOImpl currencyRateDAO;
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

    @SneakyThrows
    @Test
    public void testTransfer() {
        Bank bank = new Bank();
        Bank bank1 = new Bank();
        bank.setName("testTransfer01 " + Calendar.getInstance().getTime());
        bank.setTransferFeeInPercent(10);
        bank.setTransferFeeInPercentForNotNaturalPersons(20);

        bank1.setTransferFeeInPercent(15);
        bank1.setTransferFeeInPercentForNotNaturalPersons(25);
        bank1.setName("testTransfer02 " + Calendar.getInstance().getTime());
        bankService.persist(bank);
        bankService.persist(bank1);

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

        currencyRateDAO.save(currencyRateUsd);
        currencyRateDAO.save(currencyRateEur);
        currencyRateDAO.save(currencyRateUsd1);
        currencyRateDAO.save(currencyRateEur1);

        bankService.update(bank);
        bankService.update(bank1);

        Client client1 = new Client();
        Client client2 = new Client();
        client1.setName("ClientTransactionTest");
        client1.setNaturalPerson(true);
        client2.setName("ClientTransactionTest1");
        client2.setNaturalPerson(false);
        clientService.persist(client1);
        clientService.persist(client2);

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

        accountService.persist(account1);
        accountService.persist(account1p2);
        accountService.persist(account2);
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
        //как это было с ленивой инициализацией
        //  account1fDB.setClient((Client) Hibernate.unproxy(account1fDB.getClient()));
        //account2fDB.setClient((Client) Hibernate.unproxy(account2fDB.getClient()));
        accountService.transferAmount(account1fDB, account2fDB, 50);
    }

    @SneakyThrows
    @Test
    public void testBankCRUD() {
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("testBankCRUD" + Calendar.getInstance().getTime());
        b2.setName("testBankCRUD2 " + Calendar.getInstance().getTime());
        b1.setTransferFeeInPercentForNotNaturalPersons(70);
        b2.setTransferFeeInPercentForNotNaturalPersons(70);
        int n1 = bankDAO.save(b1);
        int n2 = bankDAO.save(b2);
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

        bankDAO.delete(b1);
        bankDAO.delete(b2);
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
        int n1 = clientDAO.save(c1);
        int n2 = clientDAO.save(c2);
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

        clientDAO.delete(c1);
        clientDAO.delete(c2);
        assertThrows(EntityNotFoundException.class, () -> {
            clientDAO.read(n1);
        });
        assertThrows(EntityNotFoundException.class, () -> {
            clientDAO.read(n2);
        });
    }

    @SneakyThrows
    @Test
    public void testCurrencyRateCRUD() {
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
        currencyRateDAO.save(cr1);
        currencyRateDAO.save(cr2);
        currencyRateDAO.save(cr3);
        currencyRateDAO.save(cr4);

        assertEquals(cr1, currencyRateDAO.read(cr1.getBank().getId(), cr1.getCurrency()));
        assertEquals(cr4, currencyRateDAO.read(cr4.getBank().getId(), cr4.getCurrency()));

    }

}