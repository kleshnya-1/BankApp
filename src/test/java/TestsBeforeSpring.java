import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class TestsBeforeSpring {

    @SneakyThrows
    @Test
    public void testAppCont() {

//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//
//        CurrencyConverter currencyConverter = context.getBean(CurrencyConverter.class);
//        CurrencyRateDAOImpl currencyRateDAO = context.getBean(CurrencyRateDAOImpl.class);
//        BankDAOImpl bankDAO = context.getBean(BankDAOImpl.class);
//
//        System.out.println(currencyConverter);
//        System.out.println(currencyRateDAO);
//        System.out.println(bankDAO);
//        Bank b = bankDAO.read(288);
//        System.out.println(b);

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
        CurrencyRateDAOImpl currencyRateDAO = new CurrencyRateDAOImpl();

        b1.getCurrencyRates().add(cr1);
        b2.getCurrencyRates().add(cr2);
        b2.getCurrencyRates().add(cr3);
        b2.getCurrencyRates().add(cr4);
        currencyRateDAO.save(cr1);
        currencyRateDAO.save(cr2);
        currencyRateDAO.save(cr3);
        currencyRateDAO.save(cr4);

        assertEquals(cr1, currencyRateDAO.read(cr1.getBank().getId(), cr1.getCurrency()));
        assertEquals(cr4, currencyRateDAO.read(cr4.getBank().getId(), cr4.getCurrency()));

    }

    @SneakyThrows
    @Test
    public void testBankCRUD() {
        BankDAOImpl bankDAO = new BankDAOImpl();
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
    public void testBankAndCurrRatesCRUD() {
        BankDAOImpl bankDAO = new BankDAOImpl();
        CurrencyRateDAOImpl currencyRateDAO = new CurrencyRateDAOImpl();
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("testBankAndCurrRatesCRUD " + Calendar.getInstance().getTime());
        b2.setName("testBankAndCurrRatesCRUD1 " + Calendar.getInstance().getTime());
        b1.setTransferFeeInPercentForNotNaturalPersons(70);
        b2.setTransferFeeInPercentForNotNaturalPersons(70);
        int n1 = bankDAO.save(b1);
        int n2 = bankDAO.save(b2);

        Bank b1Fdb = bankDAO.read(n1);
        Bank b2Fdb = bankDAO.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        CurrencyRate currencyRateUsd = new CurrencyRate(Currency.USD, 1000);
        CurrencyRate currencyRateEur = new CurrencyRate(Currency.EUR, 1200);
        CurrencyRate currencyRateUsd1 = new CurrencyRate(Currency.USD, 1010);
        CurrencyRate currencyRateEur1 = new CurrencyRate(Currency.EUR, 1210);
        CurrencyRate currencyRateUsd2 = new CurrencyRate(Currency.USD, 1020);
        CurrencyRate currencyRateEur2 = new CurrencyRate(Currency.EUR, 1220);
        currencyRateUsd.setBank(b1);
        currencyRateEur.setBank(b1);
        currencyRateUsd1.setBank(b1);
        currencyRateEur1.setBank(b1);
        currencyRateUsd2.setBank(b2);
        currencyRateEur2.setBank(b2);
        currencyRateUsd.getBank().setId(b1.getId());
        currencyRateEur.getBank().setId(b1.getId());
        currencyRateUsd1.getBank().setId(b1.getId());
        currencyRateEur1.getBank().setId(b1.getId());
        currencyRateUsd2.getBank().setId(b2.getId());
        currencyRateEur2.getBank().setId(b2.getId());

        b1.getCurrencyRates().add(currencyRateUsd);
        b1.getCurrencyRates().add(currencyRateEur);
        b1.getCurrencyRates().add(currencyRateUsd1);
        b1.getCurrencyRates().add(currencyRateEur1);
        b2.getCurrencyRates().add(currencyRateUsd2);
        b2.getCurrencyRates().add(currencyRateEur2);
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

        CurrencyRate lastCurr = currencyRateDAO.read(b1.getId(), currencyRateEur1.getCurrency());
        assertEquals(lastCurr, currencyRateEur1);
        bankDAO.delete(b1);
        bankDAO.delete(b2);

        b1Fdb = bankDAO.read(n1);
        b2Fdb = bankDAO.read(n2);
        assertNull(b1Fdb);
        assertNull(b2Fdb);
    }
}
