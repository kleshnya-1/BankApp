package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private int id;
    private int bankId;
    private String bankName;
    private String clientName;
    private AccountAmount accountAmount;
    private Currency currency;
    private double amount;

//    public Currency getCurrency() {
//        return accountAmount.getCurrency();
//    }
//
//    public double getAmount() {
//        return accountAmount.getAmount();
//    }
//
//    public void setCurrency(Currency currency) {
//        accountAmount.setCurrency(currency);
//    }
//
//    public void setAmount(double amount) {
//        accountAmount.setAmount(amount);
//    }
}
