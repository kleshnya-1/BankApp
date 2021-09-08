package ru.laptseu.bankapp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.DaoFactory;

@SpringBootApplication
@ComponentScan("ru.laptseu.bankapp")
//@RestController
public class Main {


    public static void main(String[] args) {
       // ApplicationContext context =            new AnnotationConfigApplicationContext(SpringConfig.class, MongoConfig.class, PgConfig.class);
//        context.getBean(BankDAOImpl.class);
//
//        context.getBeanDefinitionNames().toString();
        SpringApplication.run(Main.class, args);
    }
}
