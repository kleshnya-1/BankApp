package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bank {
    private int id;
    private String name;
    private double transferFeeInPercent;
    private double transferFeeInPercentForNotNaturalPersons;
    private List<CurrencyRate> rateList;
}
