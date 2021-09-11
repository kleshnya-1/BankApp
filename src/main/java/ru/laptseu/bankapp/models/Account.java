package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Calendar;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends EntityModel {
    //проблемы. сринг бины умные и закрывают сессию. вручную анпрокси
    // невозможна. а иначе аккаунт заходит на тест с ленивой
    // инициализацией банка и клиента.
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double amount;
    private String accNumber = Calendar.getInstance().getTime() + "";
}
