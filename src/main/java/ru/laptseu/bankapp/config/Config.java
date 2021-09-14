package ru.laptseu.bankapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.laptseu.bankapp.dao.*;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration
@PropertySource(value = "classpath:db.properties")
public class Config {

   private final Environment environment;

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
вот это не работает. при том апликэйшн он хорошо видит. даже сам создал базу после указания
jpa:
hibernate:
ddl-auto: create
а вот это никак
      */
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix="datasource")
//    public DataSource dataSource(){
//        return DataSourceBuilder
//                .create()
//                .build();
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
//EntityManagerFactory factory;
//   private final
//   DataSource dataSource;

}

