package ru.laptseu.bankapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration
@PropertySource(value = "classpath:db.properties")
public class Config {

   private final Environment environment;




// этот бин я использую для доступа к постгрес.
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

/*
вот это не работает. при том апликэйшн он хорошо видит. даже сам создал базу после указания. в properties есть подробнее
jpa:
hibernate:
ddl-auto: create
а вот это никак
      */
   // TODO: 15.09.2021 этот бин(на встрече просил его в телегу скинуть) не создается. пробовал разные префиксы.
   //  первый, естественно, закоментировал.
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix="datasource")
//    public DataSource dataSource(){
//        return DataSourceBuilder
//                .create()
//                .build();
//    }

// TODO: 15.09.2021 читал, что этот бин необходим для транзакции.
//  на данный момент не смог проверить, но подтверждения не нашел этому.
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
}

