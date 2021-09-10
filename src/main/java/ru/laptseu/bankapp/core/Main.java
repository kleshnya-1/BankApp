package ru.laptseu.bankapp.core;

import com.mongodb.client.MongoCollection;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.laptseu.bankapp.dao.BankDAOImpl;

@SpringBootApplication
@ComponentScan("ru.laptseu.bankapp")
//@RestController
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.laptseu.bankapp");
        context.getBean(BankDAOImpl.class);
        context.getBean(MongoCollection.class);
        context.getBean(Session.class);

        context.getBeanDefinitionNames().toString();
        //  SpringApplication.run(Main.class, args);

    }
}
