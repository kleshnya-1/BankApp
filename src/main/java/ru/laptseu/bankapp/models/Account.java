package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double amount;
}
