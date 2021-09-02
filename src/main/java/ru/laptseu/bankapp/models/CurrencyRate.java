package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

//todo add reading from file DAO
@Getter
@Setter
public class CurrencyRate {
    private int id;
    private int bankId;
    private Currency currency;
    private double rateToByn;
}
