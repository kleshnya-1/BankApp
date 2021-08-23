package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private int id;
    private int bankId;
    private String clientName;
    private Currency currency;
    private double amount;
}
