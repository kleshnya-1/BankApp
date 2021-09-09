package ru.laptseu.bankapp.core;

import com.mongodb.client.MongoCollection;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.CurrencyRateDAOImpl;
import ru.laptseu.bankapp.models.CustomDocument;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUnstatic;
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

    @Bean
    public CurrencyRateDAOImpl currencyRateDAO() {
        return new CurrencyRateDAOImpl();
    }

    @Bean
    public Session sessionBean(HibernateSessionFactoryUnstatic hibernateSessionFactoryUnstatic) {
        return hibernateSessionFactoryUnstatic.getSessionFactory().openSession();
    }

    @Bean
    public BankDAOImpl bankDAO() {
        return new BankDAOImpl();
    }
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

    // private final ApplicationContext applicationContext;

//        @Bean
//    public MongoCollection mongoCollection() {
//            MongoCollection currencyRatesMongoCollection=MongoClientFactoryAndSetUp.getMongoCollection("CurrencyRates", CustomDocument.class);
//            return currencyRatesMongoCollection;
//    }
//
//
//
////    @Bean
////    public BankDAOImpl bankDAO() {
////        return new BankDAOImpl();
////    }
//
////    @Bean
////    public CurrencyRateDAOImpl currencyRateDAO() {
////        return new CurrencyRateDAOImpl();
////    }
//
//
////    @Bean
////    public SpringResourceTemplateResolver templateResolver() {
////        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
////        templateResolver.setApplicationContext(applicationContext);
////        templateResolver.setPrefix("/WEB-INF/views/");
////        templateResolver.setSuffix(".html");
////        return templateResolver;
////    }
////
////    @Bean
////    public SpringTemplateEngine templateEngine() {
////        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
////        templateEngine.setTemplateResolver(templateResolver());
////        templateEngine.setEnableSpringELCompiler(true);
////        return templateEngine;
////    }
////
////    @Override
////    public void configureViewResolvers(ViewResolverRegistry registry) {
////        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
////        resolver.setTemplateEngine(templateEngine());
////        registry.viewResolver(resolver);
////    }
////
////
////    @Bean
////    public DataSource dataSource (){
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName("org.postgresql.Driver");
////        dataSource.setUrl("jdbc:postgresql://localhost:5432/database_bank");
////        dataSource.setUsername("postgres");
////        dataSource.setPassword("1");
////        return dataSource;
////
////    }
////    @Bean
////    public JdbcTemplate jdbcTemplate(){
////        return new JdbcTemplate(dataSource());
////    }
//
//
//
}
