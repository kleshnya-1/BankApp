package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends EntityWithIntegerId {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private double amount;
    private String accNumber = Calendar.getInstance().getTime() + "";
}
