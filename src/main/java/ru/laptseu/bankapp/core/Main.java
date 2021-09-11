package ru.laptseu.bankapp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.laptseu.bankapp")
//@RestController
public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext("ru.laptseu.bankapp");
//        context.getBean(BankDAOImpl.class);
//        context.getBean(MongoCollection.class);
//        context.getBean(Session.class);
//
//        context.getBeanDefinitionNames().toString();
        SpringApplication.run(Main.class, args);

    }
}
