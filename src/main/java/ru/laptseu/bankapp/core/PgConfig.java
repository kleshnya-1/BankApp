//package ru.laptseu.bankapp.core;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class PgConfig {
//    @Bean
//    public DataSource dataSource (){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        //todo fix
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/database_bank");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("1");
//        return dataSource;
//    }
//}
