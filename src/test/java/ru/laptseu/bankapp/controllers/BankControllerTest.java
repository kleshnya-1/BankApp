//not relevant for monolith app

//package ru.laptseu.bankapp.controllers;
//
//import org.bson.types.ObjectId;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.ui.Model;
//import ru.laptseu.bankapp.Main;
//import ru.laptseu.bankapp.controllers.BankController;
//import ru.laptseu.bankapp.models.Bank;
//import ru.laptseu.bankapp.models.BankRateList;
//import ru.laptseu.bankapp.models.BankRateList;
//import ru.laptseu.bankapp.models.Currency;
//import ru.laptseu.bankapp.services.BankService;
//import ru.laptseu.bankapp.services.CurrencyRateService;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Main.class)
//class BankControllerTest {
//
//    Bank b1 = new Bank();
//    Bank b2 = new Bank();
//    int n1 = Calendar.MILLISECOND + Calendar.SECOND + hashCode();
//    int n2 = n1 + 100;
//    BankRateList bankRateListDocument1 = new BankRateList();
//    Map<Currency, Double> map1 = new HashMap<>();
//    BankRateList bankRateListDocument2 = new BankRateList();
//    Map<Currency, Double> map2 = new HashMap<>();
//    @Mock
//    private BankService bankService;
//    @Mock
//    private CurrencyRateService currencyRateService;
//    @InjectMocks
//    private BankController bankController;
//
//    @BeforeEach
//    void setUp() {
//        b1.setName("testBankController1 " + n1);
//        b2.setName("testBankController2 " + n2);
//        b1.setTransferFeeInPercentForNotNaturalPersons(70);
//        b2.setTransferFeeInPercentForNotNaturalPersons(70);
//
//        bankRateListDocument1.setBankId(n1);
//        bankRateListDocument1.setId(new ObjectId());
//        map1.put(Currency.EUR, 260d);
//        map1.put(Currency.EUR, 261d);
//        bankRateListDocument1.setCurrenciesAndRates(map1);
//        currencyRateService.save(bankRateListDocument1);
//        bankRateListDocument2.setBankId(n2);
//        bankRateListDocument2.setId(new ObjectId());
//        map2.put(Currency.USD, 360d);
//        map2.put(Currency.USD, 361d);
//        map2.put(Currency.USD, 362d);
//        bankRateListDocument2.setCurrenciesAndRates(map2);
//    }
//
//    @Test
//    void openAllBanks() {
//        //todo
//    }
//
//    @Test
//    void openBankPage() {
//        given(bankService.read(n1)).willReturn(b1);
//        given(bankService.read(n2)).willReturn(b2);
//        Bank responseEntity1 = (Bank) bankController.openBank(n1);
//        Bank responseEntity2 = (Bank) bankController.openBankPage(n2);
//        assertEquals(b1, responseEntity1);
//        assertEquals(b2, responseEntity2);
//        assertNotEquals(b1, responseEntity2);
//        assertNotEquals(b2, responseEntity1);
//    }
//
//    @Test
//    void openBankRates() {
//        given(currencyRateService.read(n1)).willReturn(bankRateListDocument1);
//        given(currencyRateService.read(n2)).willReturn(bankRateListDocument2);
//        assertEquals(bankRateListDocument1.getId(), bankController.openBankRates(n1).getId());
//        assertEquals(bankRateListDocument2.getId(), bankController.openBankRates(n2).getId());
//        assertNotEquals(bankRateListDocument2.getId(), bankController.openBankRates(n1).getId());
//        assertNotEquals(bankRateListDocument1.getId(), bankController.openBankRates(n2).getId());
//    }
//
//    @Test
//    void newBank() {
//        Bank bankForMock = new Bank();
//        bankForMock.setName("MockingBank" + hashCode() + Calendar.MILLISECOND);
//        given(bankService.save(any(Bank.class))).willReturn(bankForMock);
//        assertEquals(bankController.newBank(new Bank()), bankForMock);
//        assertEquals(bankController.newBank(b1), bankForMock);
//        assertNotEquals(bankController.newBank(b1), b1);
//    }
//}