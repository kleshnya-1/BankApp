package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Log4j2
@Component
@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account extends EntityModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    Bank bank;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double amount;
    private Integer accNumber;
}
