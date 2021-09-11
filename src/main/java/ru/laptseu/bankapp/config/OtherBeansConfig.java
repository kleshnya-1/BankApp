package ru.laptseu.bankapp.config;

import com.mongodb.client.MongoCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.laptseu.bankapp.models.CustomDocument;
import ru.laptseu.bankapp.utilities.MongoClientFactoryAndSetUp;

@Configuration
//@ComponentScan("ru.laptseu.bankapp")

public class OtherBeansConfig {

    //todo перенес сюда из хайб конфигa
    //todo set name for CurrencyRates
    @Bean
    public MongoCollection mongoCollection() {
        return MongoClientFactoryAndSetUp.getMongoCollection("CurrencyRates", CustomDocument.class);
    }

//    @Bean
//    public Session sessionBean(HibernateSessionFactoryUnstatic hibernateSessionFactoryUnstatic) {
//        return hibernateSessionFactoryUnstatic.getSessionFactory().openSession();
//    }

//    @Bean
//    public HibernateSessionFactoryUnstatic hibernateSessionFactoryUnstatic() {
//        return new HibernateSessionFactoryUnstatic();
//    }

//    @Bean
//    public CurrencyRateDAOImpl currencyRateDAO() {
//        return new CurrencyRateDAOImpl();
//    }
//    @Bean
//    public CurrencyConverter currencyConverter() {
//        return new CurrencyConverter();
//    }
//        @Bean
//    public MongoCollection mongoCollection() {
//            MongoCollection currencyRatesMongoCollection=MongoClientFactoryAndSetUp.getMongoCollection("CurrencyRates", CustomDocument.class);
//            return currencyRatesMongoCollection;
//    }

////    @Bean
////    public BankDAOImpl bankDAO() {
////        return new BankDAOImpl();
////    }
//
////    @Bean
////    public CurrencyRateDAOImpl currencyRateDAO() {
////        return new CurrencyRateDAOImpl();
////    }

}
