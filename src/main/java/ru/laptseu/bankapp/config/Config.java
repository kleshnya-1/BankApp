package ru.laptseu.bankapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.laptseu.bankapp.dao.*;

@Configuration
@RequiredArgsConstructor
public class Config {
    //part for factories
    private final AccountDAOImpl accountDAO;
    private final BankDAOImpl bankDAO;
    private final ClientDAOImpl clientDAO;
    private final MongoBankRateDAOImpl currencyRateDAO;
    private final TransferHistoryDAOImpl transferHistoryDAO;
//    @Bean
//    DaoFactory daoFactory() {
//        return new DaoFactory(accountDAO, bankDAO, clientDAO, currencyRateDAO, transferHistoryDAO);
//    }






//    @Bean(name = “entityManagerFactory”)
//    public LocalContainerEntityManagerFactoryBean factory() {
//        LocalContainerEntityManagerFactoryBean factory = …
//        factory.setDataSource(dataSource);
//        factory.setPackagesToScan(
//                new String[] {“your.package”});
//        factory.setJpaVendorAdapter(
//                new HibernateJpaVendorAdapter());
//        return factory;
//    }

//
//   private final
//    EntityManagerFactory factory;
//   private final
//    DataSource dataSource;
//
//    @Bean//(name = “transactionManager”)
//    public PlatformTransactionManager platformTransactionManager() {
//        JpaTransactionManager tm =
//                new JpaTransactionManager();
//        tm.setEntityManagerFactory(factory);
//        tm.setDataSource(dataSource);
//        return tm;
    //  }
}
