package ru.laptseu.bankapp;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.DriverManager;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
      //  DriverManager.getConnection("jdbc:h2:mem:");


        SpringApplication.run(Main.class, args);
    }
}
