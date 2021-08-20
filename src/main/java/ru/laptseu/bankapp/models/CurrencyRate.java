package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRate {
    private int bankId;
    private Currency currency;
    private double rateToByn;
}
