package ru.laptseu.bankapp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.laptseu.bankapp")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
