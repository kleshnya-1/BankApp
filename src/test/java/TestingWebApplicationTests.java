import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.laptseu.bankapp.core.Main;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
//@ComponentScan("main.ru.laptseu.bankapp")
@SpringBootTest(classes= Main.class)
public class TestingWebApplicationTests {

    @Autowired
    BankDAOImpl bankDAO;
@Autowired
    CurrencyRateDAOImpl currencyRateDAO;

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
        b1Fdb = bankDAO.read(n1);
        b2Fdb = bankDAO.read(n2);
        assertNull(b1Fdb);
        assertNull(b2Fdb);
    }

    @SneakyThrows
    @Test
    public void testCurrencyRateCRUD() {
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("TestBank1" + Calendar.getInstance().getTime());
        b2.setName("TestBank2" + Calendar.getInstance().getTime());

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
        currencyRateDAO.save(cr1);
        currencyRateDAO.save(cr2);
        currencyRateDAO.save(cr3);
        currencyRateDAO.save(cr4);

        CurrencyRate a = currencyRateDAO.read(cr1.getBankId(), cr1.getCurrency());
        CurrencyRate a1 = currencyRateDAO.read(cr4.getBankId(), cr4.getCurrency());

        assertEquals(cr1, currencyRateDAO.read(cr1.getBankId(), cr1.getCurrency()));
        assertEquals(cr4, currencyRateDAO.read(cr4.getBankId(), cr4.getCurrency()));

    }

}