package ru.laptseu.bankapp.core;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

//@Configuration
//@ComponentScan("ru.laptseu.bankapp")
@Data
public class SpringConfig {

    @Autowired
    ApplicationContext context;
    // ApplicationContext context ;//= new AnnotationConfigApplicationContext(SpringConfig.class);

//    @Bean
//    public BankDAOImpl bankDAO() {
//        return new BankDAOImpl();
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

//    @Autowired
//    public SpringConfig(ApplicationContext applicationCont) {
//        this.applicationContext = applicationCont;
//    }

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
