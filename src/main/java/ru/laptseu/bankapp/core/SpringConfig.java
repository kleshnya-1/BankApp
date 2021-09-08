//package ru.laptseu.bankapp.core;
//
//import lombok.Data;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ru.laptseu.bankapp.dao.BankDAOImpl;
//import ru.laptseu.bankapp.models.Account;
//import ru.laptseu.bankapp.models.Bank;
//import ru.laptseu.bankapp.models.Client;
//import ru.laptseu.bankapp.models.TransferHistory;
//
//@Configuration
////@ComponentScan("ru.laptseu.bankapp")
//
//public class SpringConfig {
//
//    @Bean
//    public BankDAOImpl bankDAO(){
//        return new BankDAOImpl();
//    }
//
////    @Bean
////    public SessionFactory getSessionFactory() {
////          SessionFactory sessionFactory =null;
////        try {
////                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
////                configuration.addAnnotatedClass(Account.class);
////                configuration.addAnnotatedClass(Bank.class);
////                configuration.addAnnotatedClass(Client.class);
////                //todo configuration.addAnnotatedClass(Currency.class);
////                configuration.addAnnotatedClass(TransferHistory.class);
////                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
////                sessionFactory = configuration.buildSessionFactory(builder.build());
////            } catch (Exception e) {
////                System.out.println("Исключение!" + e);
////            }
////
////        return sessionFactory;
////    }
//
//
//
//  //  ApplicationContext context;
//    // ApplicationContext context ;//= new AnnotationConfigApplicationContext(SpringConfig.class);
//
////    @Bean
////    public BankDAOImpl bankDAO() {
////        return new BankDAOImpl();
////    }
////    @Bean
////    public CurrencyRateDAOImpl currencyRateDAO() {
////        return new CurrencyRateDAOImpl();
////    }
////    @Bean
////    public CurrencyConverter currencyConverter() {
////        return new CurrencyConverter();
////    }
//
//    // private final ApplicationContext applicationContext;
//
////    @Autowired
////    public SpringConfig(ApplicationContext applicationCont) {
////        this.applicationContext = applicationCont;
////    }
//
////        @Bean
////    public MongoCollection mongoCollection() {
////            MongoCollection currencyRatesMongoCollection=MongoClientFactoryAndSetUp.getMongoCollection("CurrencyRates", CustomDocument.class);
////            return currencyRatesMongoCollection;
////    }
////
////
////
//////    @Bean
//////    public BankDAOImpl bankDAO() {
//////        return new BankDAOImpl();
//////    }
////
//////    @Bean
//////    public CurrencyRateDAOImpl currencyRateDAO() {
//////        return new CurrencyRateDAOImpl();
//////    }
////
////
//////    @Bean
//////    public SpringResourceTemplateResolver templateResolver() {
//////        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//////        templateResolver.setApplicationContext(applicationContext);
//////        templateResolver.setPrefix("/WEB-INF/views/");
//////        templateResolver.setSuffix(".html");
//////        return templateResolver;
//////    }
//////
//////    @Bean
//////    public SpringTemplateEngine templateEngine() {
//////        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//////        templateEngine.setTemplateResolver(templateResolver());
//////        templateEngine.setEnableSpringELCompiler(true);
//////        return templateEngine;
//////    }
//////
//////    @Override
//////    public void configureViewResolvers(ViewResolverRegistry registry) {
//////        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//////        resolver.setTemplateEngine(templateEngine());
//////        registry.viewResolver(resolver);
//////    }
//////
//////
//////    @Bean
//////    public DataSource dataSource (){
//////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//////        dataSource.setDriverClassName("org.postgresql.Driver");
//////        dataSource.setUrl("jdbc:postgresql://localhost:5432/database_bank");
//////        dataSource.setUsername("postgres");
//////        dataSource.setPassword("1");
//////        return dataSource;
//////
//////    }
//////    @Bean
//////    public JdbcTemplate jdbcTemplate(){
//////        return new JdbcTemplate(dataSource());
//////    }
////
////
////
//}
