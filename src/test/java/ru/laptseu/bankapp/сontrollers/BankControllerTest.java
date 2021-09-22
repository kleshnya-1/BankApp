package ru.laptseu.bankapp.сontrollers;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.laptseu.bankapp.Main;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.testModels.BankForTest;
import ru.laptseu.bankapp.models.testModels.BankRateListDocumentForTest;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
class BankControllerTest {


    @Mock
    private BankService bankService;
    @Mock
    private CurrencyRateService currencyRateService;
    @InjectMocks
    private BankController bankController;


    Bank b1 = new BankForTest();
    Bank b2 = new BankForTest();
    int n1 = Calendar.MILLISECOND + Calendar.SECOND + hashCode();
    int n2 = n1 + 100;

    BankRateListDocument bankRateListDocument1 = new BankRateListDocumentForTest();
    Map<Currency, Double> map1 = new HashMap<>();
    BankRateListDocument bankRateListDocument2 = new BankRateListDocumentForTest();
    Map<Currency, Double> map2 = new HashMap<>();


    @BeforeEach
    void setUp() {
        b1.setName("testBankController1 " + n1);
        b2.setName("testBankController2 " + n2);
        b1.setTransferFeeInPercentForNotNaturalPersons(70);
        b2.setTransferFeeInPercentForNotNaturalPersons(70);

        bankRateListDocument1.setBankId(n1);
        bankRateListDocument1.setId(new ObjectId());
        map1.put(Currency.EUR, 260d);
        map1.put(Currency.EUR, 261d);
        bankRateListDocument1.setCurrenciesAndRates(map1);
        currencyRateService.save(bankRateListDocument1);
        bankRateListDocument2.setBankId(n2);
        bankRateListDocument2.setId(new ObjectId());
        map2.put(Currency.USD, 360d);
        map2.put(Currency.USD, 361d);
        map2.put(Currency.USD, 362d);
        bankRateListDocument2.setCurrenciesAndRates(map2);
    }

    @Test
    void openAllBanks() {
        //todo
    }

    @Test
    void openBankPage() {
        given(bankService.read(n1)).willReturn(b1);
        given(bankService.read(n2)).willReturn(b2);
        BankForTest responseEntity1 = (BankForTest) bankController.openBankPage(n1);
        BankForTest responseEntity2 = (BankForTest) bankController.openBankPage(n2);
        assertEquals(b1, responseEntity1);
        assertEquals(b2, responseEntity2);
        assertNotEquals(b1, responseEntity2);
        assertNotEquals(b2, responseEntity1);
    }

    @Test
    void openBankRates() {
        given(currencyRateService.read(n1)).willReturn(bankRateListDocument1);
        given(currencyRateService.read(n2)).willReturn(bankRateListDocument2);
        assertEquals(bankRateListDocument1, bankController.openBankRates(n1));
        assertEquals(bankRateListDocument2, bankController.openBankRates(n2));
        assertNotEquals(bankRateListDocument2, bankController.openBankRates(n1));
        assertNotEquals(bankRateListDocument1, bankController.openBankRates(n2));
    }

    @Test
    void newBank() {
        Bank bankForMock = new Bank();
        bankForMock.setName("MockingBank" + hashCode() + Calendar.MILLISECOND);
        given(bankService.save(any(Bank.class))).willReturn(bankForMock);
        assertEquals(bankController.newBank(new Bank()), bankForMock);
        assertEquals(bankController.newBank(b1), bankForMock);
        assertNotEquals(bankController.newBank(b1), b1);
    }
}