package ru.laptseu.bankapp.core;

import com.mongodb.client.MongoCollection;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.postgresql.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.DaoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan("ru.laptseu.bankapp")
//@RestController
public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext context =            new AnnotationConfigApplicationContext("ru.laptseu.bankapp");
        context.getBean(BankDAOImpl.class);
        context.getBean(MongoCollection.class);
        context.getBean(Session.class);

        context.getBeanDefinitionNames().toString();
     //   SpringApplication.run(Main.class, args);

    }
}
