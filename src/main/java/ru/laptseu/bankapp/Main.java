package ru.laptseu.bankapp;

import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.DriverManager;

@SpringBootApplication
public class Main {
    //todo in root
    @SneakyThrows
    public static void main(String[] args) {
        DriverManager.getConnection("jdbc:h2:mem:");


        //SpringApplication.run(Main.class, args);
    }
}
