package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//todo add reading from file DAO
@Getter
@Setter
@Entity
@Table(name ="Currency_Rates" )
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    Bank bank;
    private int bankId;
    private Currency currency;
    private double rateToByn;
}
