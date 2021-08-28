package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//todo add reading from file DAO
@Getter
@Setter
@Entity
@Table(name = "currency_rates")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    Bank bank;
    //private int bankId;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double rateToByn;

}
