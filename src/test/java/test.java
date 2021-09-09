//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;
//import lombok.SneakyThrows;
//import lombok.extern.log4j.Log4j2;
//import org.bson.codecs.configuration.CodecRegistries;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.ClassModel;
//import org.bson.codecs.pojo.PojoCodecProvider;
//import org.junit.jupiter.api.Test;
//import ru.laptseu.bankapp.dao.AccountDAOImpl;
//import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
//import ru.laptseu.bankapp.models.*;
//import ru.laptseu.bankapp.services.AccountService;
//import ru.laptseu.bankapp.services.BankService;
//import ru.laptseu.bankapp.services.ClientService;
//import ru.laptseu.bankapp.services.CurrencyRateService;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Locale;
//
//import static com.mongodb.client.model.Filters.eq;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//@Log4j2
//public class test {
//
//    @SneakyThrows
//    @Test
//    public void testBankToRateRelationship() {
//
//        BankService bankService = new BankService();
//        CurrencyRateService currencyRateService = new CurrencyRateService();
//
//        Bank bank = new Bank();
//        Bank bank1 = new Bank();
//        bank.setName("1a1aa");
//        bank1.setName("1b1bb");
//
//        CurrencyRate currencyRateUsd = new CurrencyRate();
//        CurrencyRate currencyRateEur = new CurrencyRate();
//        currencyRateUsd.setCurrency(Currency.USD);
//        currencyRateUsd.setRateToByn(100);
//        currencyRateEur.setCurrency(Currency.EUR);
//        currencyRateEur.setRateToByn(100);
//
//        bank.getCurrencyRates().add(currencyRateEur);
//        bank.getCurrencyRates().add(currencyRateUsd);
//
//        bankService.persist(bank);
//        bankService.persist(bank1);
//
//        //bank.addRate(currencyRateEur);
//        // bank.addRate(currencyRateUsd);
//        bankService.update(bank);
//        bankService.update(bank1);
//        System.out.println();
//
//    }
//
//    @SneakyThrows
//    @Test
//    public void testClientToAccountRelationship() {
//        AccountService accountService = new AccountService();
//        ClientService clientService = new ClientService();
//        BankService bankService = new BankService();
//        Client client1 = new Client();
//        Client client2 = new Client();
//
//        Account account1 = new Account();
//        Account account1p2 = new Account();
//        Account account2 = new Account();
//        client1.setName("testClient1");
//        client2.setName("testClient2");
//        clientService.persist(client1);
//        clientService.persist(client2);
//
////        Bank bank = new Bank();
////        Bank bank1 = new Bank();
////        bank.setName("1a1aa");
////        bank1.setName("1b1bb");
////        bankService.persist(bank);
////        bankService.persist(bank1);
//
//        account1.setAmount(100);
//        account1p2.setAmount(120);
//        //  account1p2.setBank(bank);
//        account2.setAmount(200);
//
//        account1.setClient(client1);
//        //accountService.persist(account1);
//
//        client1.addAccount(account1);
//        client1.addAccount(account1p2);
//        client2.addAccount(account2);
//
//        clientService.update(client1);
//        clientService.update(client2);
//        System.out.println();
//
//    }
//
//    @SneakyThrows
//    @Test
//    public void testCurrencyRateCRUD() {
//
//        CurrencyRateDAOImpl currencyRateDAO = new CurrencyRateDAOImpl();
//
//        List l = currencyRateDAO.read(146);
//        System.out.println(l);
////        Bank b1 = new Bank();
////        Bank b2 = new Bank();
////        b1.setName("TestBank1" + Calendar.getInstance().getTime());
////        b2.setName("TestBank2" + Calendar.getInstance().getTime());
////        b1.setId(999);
////        b2.setId(1999);
////
////        CurrencyRate cr1 = new CurrencyRate();
////        cr1.setCurrency(Currency.EUR);
////        cr1.setRateToByn(260);
////        cr1.setBank(b1);
////        CurrencyRate cr2 = new CurrencyRate();
////        cr2.setCurrency(Currency.USD);
////        cr2.setRateToByn(360);
////        cr2.setBank(b2);
////
////        currencyRateDAO.save(cr1);
////        currencyRateDAO.save(cr2);
//
////
////        CurrencyRate cr1FrDB = currencyRateDAO.read(b1.getId());
////        CurrencyRate cr2FrDB = currencyRateDAO.read(b2.getId());
//
//        System.out.println();
//
//    }
//

//
//    @Test
//    public void testBank() throws SQLException {
//        BankService bankService = new BankService();
//
//        Bank bank = new Bank();
//        Bank bank1 = new Bank();
//
//        bank.setName("good first bak1");
//        bank1.setName("good seckond bak1");
//
//        int i = 0;
//        int i2 = 0;
//
//        i = bankService.persist(bank);
//        i2 = bankService.persist(bank1);
//
//        Bank bankf = bankService.read(i);
//        Bank bank1f = bankService.read(i2);
//        System.out.println(bank1f + " " + bank1f);
//    }
//
//    @SneakyThrows
//    @Test
//    public void testCurrency2() {
//        String usd = "usd";
//        String byn = "byn";
//        String eur = "eur";
//
//        Currency currUsd = Currency.valueOf(usd.toUpperCase(Locale.ROOT));
//        Currency currByn = Currency.valueOf(byn.toUpperCase(Locale.ROOT));
//        Currency currEur = Currency.valueOf(eur.toUpperCase(Locale.ROOT));
//        CurrencyRateService currencyRateService = new CurrencyRateService();
//
//        CurrencyRate currencyRateUsd = new CurrencyRate();
//        CurrencyRate currencyRateEur = new CurrencyRate();
//        currencyRateUsd.setCurrency(Currency.USD);
//        currencyRateUsd.setRateToByn(30);
//        // currencyRateUsd.setBankId(500);
//        currencyRateEur.setCurrency(Currency.EUR);
//        currencyRateEur.setRateToByn(50);
//        // currencyRateEur.setBankId(500);
//
//        currencyRateService.persist(currencyRateEur);
//        currencyRateService.persist(currencyRateUsd);
//
//        CurrencyRate currencyRate = currencyRateService.getLastCurrency(Currency.USD, 500);
//        CurrencyRate currencyRate2 = currencyRateService.getLastCurrency(Currency.EUR, 500);
//        System.out.println(currencyRate2);
//    }
//
//    @SneakyThrows
//    @Test
//    public void testCurrency() {
//        String usd = "usd";
//        String byn = "byn";
//        String eur = "eur";
//
//        Currency currUsd = Currency.valueOf(usd.toUpperCase(Locale.ROOT));
//        Currency currByn = Currency.valueOf(byn.toUpperCase(Locale.ROOT));
//        Currency currEur = Currency.valueOf(eur.toUpperCase(Locale.ROOT));
//
////        System.out.println(currUsd);
////        System.out.println(currByn);
////        System.out.println(currEur);
//
//        assertEquals(usd.toUpperCase(Locale.ROOT), currUsd.toString());
//        assertEquals(byn.toUpperCase(Locale.ROOT), currByn.toString());
//        assertEquals(eur.toUpperCase(Locale.ROOT), currEur.toString());
//
//        CurrencyRateService currencyRateService = new CurrencyRateService();
//
//        CurrencyRate currencyRateUsd = new CurrencyRate();
//        CurrencyRate currencyRateEur = new CurrencyRate();
//        currencyRateUsd.setCurrency(Currency.USD);
//        currencyRateUsd.setRateToByn(3);
//        currencyRateEur.setCurrency(Currency.EUR);
//        currencyRateEur.setRateToByn(5);
//        //MongoCollection<CurrencyRate> currencyRatesMongo = MongoClientFactoryAndSetUp.getMongoCollection(CurrencyRate.class);
//
//        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
//                MongoClientSettings.getDefaultCodecRegistry(),
//                CodecRegistries.fromProviders(PojoCodecProvider.builder()
//                        .register(
//                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build(),
//                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build()
//
//                        ).automatic(true)
//                        .build()
//                )
//        );
//        MongoCollection<CurrencyRate> collection = new MongoClient(new MongoClientURI("mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test"))
//                .getDatabase("123")
//                .withCodecRegistry(codecRegistry).getCollection("CurrencyRateMD", CurrencyRate.class);
//
//        System.out.println("Connected to database!");
//
//        CurrencyRate ca = collection.find(eq("_id", 900)).first();
//        System.out.println();
//
//    }
//
//    @Test
//    public void testAddClient() {
//        Client client = new Client();
//        client.setName("firstTestClient");
//        client.setNaturalPerson(true);
//
//        ClientService clientService = new ClientService();
//        try {
//            clientService.persist(client);
//        } catch (SQLException es) {
//            es.printStackTrace();
//        }
//
//    }
//
//    @Test
//    void testAccount() {
//
//        AccountDAOImpl accountDAO = new AccountDAOImpl();
//
//        Account accountId951 = new Account();
//        Account accountId0 = new Account();
//        Account accountIdMin900 = new Account();
//
////        accountId951.setClientName("nameClient");
////        accountId0.setClientName("nameClient");
////        accountIdMin900.setClientName("nameClient");
////        accountId951.setBankName("goodBank");
////        accountId0.setBankName("goodBank");
////        accountIdMin900.setBankName("goodBank");
//        accountId951.setAmount(100);
//        accountId0.setAmount(100);
//        accountIdMin900.setAmount(100);
//        accountId951.setCurrency(Currency.USD);
//        accountId0.setCurrency(Currency.USD);
//        accountIdMin900.setCurrency(Currency.USD);
//
//        int initAccountId1 = 951;
//        int initAccountId2 = 0;
//        int initAccountId3 = -900;
//        log.debug("init account id " + initAccountId1 + " " + initAccountId2 + " " + initAccountId3);
//
//        accountId951.setId(initAccountId1);
//        accountId0.setId(initAccountId2);
//        accountIdMin900.setId(initAccountId3);
//
//        int accountId1 = accountId951.getId();
//        int accountId2 = accountId0.getId();
//        int accountId3 = accountIdMin900.getId();
//
//        try {
//            accountId1 = accountDAO.save(accountId951);
//            accountId2 = accountDAO.save(accountId0);
//            accountId3 = accountDAO.save(accountIdMin900);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        log.debug("database account id " + accountId1 + " " + accountId2 + " " + accountId3);
//
//        assertFalse(accountId951.getId() == initAccountId1);
//        assertFalse(accountId0.getId() == initAccountId2);
//        assertFalse(accountIdMin900.getId() == initAccountId3);
//
//        assertEquals(accountId951.getId(), accountId1);
//        assertEquals(accountId0.getId(), accountId2);
//        assertEquals(accountIdMin900.getId(), accountId3);
//    }
//}
