package ru.laptseu.bankapp.core;

import lombok.SneakyThrows;
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
       // ApplicationContext context =            new AnnotationConfigApplicationContext(SpringConfig.class, MongoConfig.class, PgConfig.class);
//        context.getBean(BankDAOImpl.class);
//
//        context.getBeanDefinitionNames().toString();
        SpringApplication.run(Main.class, args);

     /*   String url = "jdbc:postgresql://localhost:5432/database_bank";
        String username = "postgres";
        String password = "1";
        System.out.println("Connecting...");

        Connection connection = null;

        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);

            Class.forName("org.postgresql.Driver"); //нужен ли он ??

            System.out.println(driver.toString());
            System.out.println("Драйвер успешно зарегистрирован");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось загрузить класс драйвера");
        }

        try {

            connection = DriverManager.getConnection(url,
                    username, password);
            System.out.println(connection);

            if(! connection.isClosed()){
                System.out.println("Соединение установлено");
            }
        }catch (SQLException e){
            System.err.println("Соединение не установлено");
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
                System.out.println("Соединение закрыто");
            }

        }*/


    }
}
